package com.vanstone.weedfs.client.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vanstone.fs.ContentType;
import com.vanstone.fs.FSFile;
import com.vanstone.weedfs.client.FSClient;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.ServerLocations;
import com.vanstone.weedfs.client.WeedAssignedInfo;
import com.vanstone.weedfs.conf.WeedFSConf;

/**
 * @author shipeng
 */
public class WeedFSClient implements FSClient {

	private static Logger LOG = LoggerFactory.getLogger(WeedFSClient.class);
	
	/**
	 * MasterAddress
	 */
	private String masterAddress;
	/**
	 * MasterPort
	 */
	private String masterPort;
	
	/**
	 * 默认构造函数,默认使用当前配置信息中的信息<br/> 
	 * 可直接配置到Spring上下文环境中
	 */
	public WeedFSClient() {
		WeedFSConf fsConf = WeedFSConf.getWeedFSConf();
		this.masterAddress = fsConf.getServerAddress();
		this.masterPort = String.valueOf(fsConf.getServerPort());
	}
	
	public WeedFSClient(String address, String port) {
		this.masterAddress = address;
		this.masterPort = port;
	}

	private WeedAssignedInfo seedAssignFidRequest() {
		WeedAssignedInfo assignedInfo = null;
		BufferedReader in = null;

		// 1. send assign request and get fid
		try {
			in = new BufferedReader(new InputStreamReader(sendHttpGetRequest("http://" + this.masterAddress + ":"	+ this.masterPort + "/", "dir/assign", "GET")));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			Gson gson = new Gson();
			assignedInfo = gson.fromJson(response.toString(), WeedAssignedInfo.class);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return assignedInfo;
	}
	
	@Override
	public RequestResult upload(InputStream inputstream, String fileName, String mimeType) {
		WeedAssignedInfo assignedInfo = seedAssignFidRequest();
		if (LOG.isDebugEnabled()) {
			Gson gson = new Gson();
			System.out.println(gson.toJson(assignedInfo));
		}
		return uploadGeneral(inputstream, assignedInfo.getFid(), assignedInfo.getPublicUrl(), fileName, mimeType);
	}

	@Override
	public RequestResult upload(byte[] data, String fileName, String mimeType) {
		WeedAssignedInfo assignedInfo = seedAssignFidRequest();
		return uploadGeneral(data, assignedInfo.getFid(), assignedInfo.getPublicUrl(), fileName, mimeType);
	}

	@Override
	public RequestResult upload(File inputFile) {
		if (!inputFile.exists()) {
			throw new IllegalArgumentException("File doesn't exist");
		}

		InputStream inStream = null;
		try {
			inStream = new FileInputStream(inputFile);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

		return upload(inStream, inputFile.getName(), ContentType.parseContentType(inputFile).getMimeType());
	}
	
	/*
	 * example: fid = 3,01637037d6 write file to local file
	 */
	@Override
	public RequestResult download(String fid, String path) {

		if (fid == null || fid.length() == 0) {
			throw new IllegalArgumentException("Fid cannot be empty");
		}

		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException("File path cannot be empty");
		}

		File output = new File(path);
		RequestResult result = new RequestResult();

		if (output.exists()) {
			throw new IllegalArgumentException("output file ");
		}

		BufferedReader in = null;

		// 2. download the file
		BufferedOutputStream wr = null;
		InputStream input = download(fid);

		try {
			output.createNewFile();
			wr = new BufferedOutputStream(new FileOutputStream(output));

			byte[] buffer = new byte[4096];
			int len = -1;
			while ((len = input.read(buffer)) != -1) {
				wr.write(buffer, 0, len);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		} finally {
			try {
				if (in != null)
					in.close();
				if (wr != null)
					wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * example: fid = 3,01637037d6 write file to local file
	 */
	@Override
	public InputStream download(String fid) {

		if (fid == null || fid.length() == 0) {
			throw new IllegalArgumentException("Fid cannot be empty");
		}

		String volumnId = fid.split(",")[0];
		ServerLocations locations = null;

		BufferedReader in = null;

		// 1. send quest to get volume address
		try {
			in = new BufferedReader(new InputStreamReader(sendHttpGetRequest("http://" + this.masterAddress + ":"
					+ this.masterPort + "/", "dir/lookup?volumeId=" + volumnId, "GET")));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			Gson gson = new Gson();
			locations = gson.fromJson(response.toString(), ServerLocations.class);

		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 2. get input stream
		try {
			return sendHttpGetRequest("http://" + locations.getOnePublicUrl() + "/", fid, "GET");
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}
	
	@Override
	public boolean downloadToFile(String id, File file) {
		try {
			RequestResult rr = this.download(id, file.getPath());
			return rr.isSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void downloadToFSFile(String fid, FSFile fsFile) {
		if (fid == null || "".equals(fid) || fsFile == null) {
			throw new IllegalArgumentException();
		}
		InputStream is = this.download(fid);
		if (fsFile.getFile() != null && is != null) {
			try {
				fsFile.getFile().createNewFile();
				byte[] buffer = new byte[4096];
				int n = -1;
				OutputStream os = fsFile.getOutputStream();
				while ((n = is.read(buffer, 0, buffer.length)) != -1) {
					os.write(buffer, 0, n);
				}
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalArgumentException(e);
			}
		}
	}
	
	/**
	 * delete the file
	 */
	@Override
	public RequestResult delete(String fid) {

		if (fid == null || fid.length() == 0) {
			throw new IllegalArgumentException("Fid cannot be empty");
		}

		RequestResult result = new RequestResult();

		String volumnId = fid.split(",")[0];
		ServerLocations locations = null;

		BufferedReader in = null;

		// 1. send quest to get volume address
		try {
			in = new BufferedReader(new InputStreamReader(sendHttpGetRequest("http://" + this.masterAddress + ":" + this.masterPort + "/", "dir/lookup?volumeId=" + volumnId, "GET")));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			Gson gson = new Gson();
			locations = gson.fromJson(response.toString(), ServerLocations.class);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 2. delete the file
		try {
			HttpURLConnection con = null;
			URL requestUrl = new URL("http://" + locations.getOnePublicUrl() + "/" + fid);
			con = (HttpURLConnection) requestUrl.openConnection();

			con.setRequestMethod("DELETE");
			// add request header
			con.setRequestProperty("User-Agent", "");
			int responseCode = con.getResponseCode();
			LOG.debug("Delete WeedFS File {}", fid);
			if (responseCode == 200 || responseCode == 202) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				LOG.error("DELETE FS FILE ERROR ,ResponseCode {}, WeedFileID {}", responseCode, fid);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
		return result;
	}

	/*
	 * Used to send request to WeedFS server
	 */
	private InputStream sendHttpGetRequest(String host, String requestUrlDetail, String method) throws Exception {

		HttpURLConnection con = null;
		URL requestUrl = new URL(host.toString() + requestUrlDetail);
		con = (HttpURLConnection) requestUrl.openConnection();

		// optional default is GET
		con.setRequestMethod(method);

		// add request header
		con.setRequestProperty("User-Agent", "");
		// for later
		// int responseCode =
		con.getResponseCode();
		return con.getInputStream();
	}

	private RequestResult uploadGeneral(Object data, String fid, String url, String fileName, String mimeType) {

		RequestResult result = new RequestResult();

		AbstractContentBody inputBody = null;

		try {
			if (Class.forName("java.io.InputStream").isInstance(data)) {
				inputBody = new InputStreamBody((InputStream) data, mimeType, fileName);
			} else {
				inputBody = new ByteArrayBody((byte[]) data, mimeType, fileName);
			}
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

		HttpClient client = new DefaultHttpClient();

		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost post = new HttpPost("http://" + url + "/" + fid);

		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		entity.addPart("fileBody", inputBody);
		try {
			entity.addPart("fileName", new StringBody(fileName));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		post.setEntity(entity);
		try {
			String response = EntityUtils.toString(client.execute(post).getEntity(), "UTF-8");
			client.getConnectionManager().shutdown();
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
			JsonElement nameElement = jsonObject.get("name");
			JsonElement sizeElement = jsonObject.get("size");
			result.setFid(fid);
			result.setSize(sizeElement != null ? sizeElement.getAsInt() : -1);
			result.setName(nameElement != null ? nameElement.getAsString() : null);
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}
	}

	@Override
	public RequestResult uploadDirectly(File inputFile) {
		if (inputFile == null) {
			throw new IllegalArgumentException();
		}
		RequestResult result = new RequestResult();
		ContentType contentType = ContentType.parseContentType(inputFile);
		if (contentType == null) {
			throw new IllegalArgumentException();
		}
		AbstractContentBody inputBody = null;
		try {
			inputBody = new InputStreamBody(new FileInputStream(inputFile), contentType.getMimeType(), inputFile.getName());
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException(e2);
		}
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		HttpPost post = new HttpPost("http://" + WeedFSConf.getWeedFSConf().getServerAddress() + ":" + WeedFSConf.getWeedFSConf().getServerPort() + "/submit" );
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		entity.addPart("fileBody", inputBody);
		try {
			entity.addPart("fileName", new StringBody(inputFile.getName()));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		post.setEntity(entity);
		try {
			String response = EntityUtils.toString(client.execute(post).getEntity(), "UTF-8");
			client.getConnectionManager().shutdown();
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
			JsonElement nameElement = jsonObject.get("name");
			JsonElement sizeElement = jsonObject.get("size");
			JsonElement fidElement = jsonObject.get("fid");
			result.setFid(fidElement != null ? fidElement.getAsString() : null);
			result.setSize(sizeElement != null ? sizeElement.getAsInt() : -1);
			result.setName(nameElement != null ? nameElement.getAsString() : null);
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}

}
