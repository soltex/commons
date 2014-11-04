package com.vanstone.weedfs.client;

/**
 * 分配WeedFS Master Server 对象
 * Class to take FS info.
 */
public class WeedAssignedInfo {
	
	private int count;
	private String fid;
	private String publicUrl;
	private String url;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "WeedAssignedInfo [count=" + count + ", fid=" + fid + ", publicUrl=" + publicUrl + ", url=" + url + "]";
	}

}
