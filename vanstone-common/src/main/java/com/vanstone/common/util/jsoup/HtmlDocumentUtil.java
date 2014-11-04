/**
 * 
 */
package com.vanstone.common.util.jsoup;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author shipeng
 */
public class HtmlDocumentUtil {
	
	/**
	 * 解析Html
	 * @param url
	 * @param charset
	 * @return
	 */
	public static Document parseDocument(String url,String charset) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
	        HttpResponse httpResponse = httpClient.execute(httpGet);
	        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
	        	return null;
	        }
	        String content = EntityUtils.toString(httpResponse.getEntity(), charset);
	        return Jsoup.parse(content);
		} catch (ClientProtocolException e) {
	        e.printStackTrace();
	        return null;
        } catch (IOException e) {
	        e.printStackTrace();
	        return null;
        } finally {
        	httpClient.getConnectionManager().shutdown();
        }
		
	}
	
	/**
	 * 获取首个Element信息
	 * @param htmlDocument
	 * @param styleclassname
	 * @return
	 */
	public static Element getFirstElementByClass(Document htmlDocument, String styleclassname) {
		if (htmlDocument == null || styleclassname == null || styleclassname.equals("")) {
			return null;
		}
		Elements elements = htmlDocument.getElementsByClass(styleclassname);
		if (elements == null || elements.size() <=0) {
			return null;
		}
		return elements.get(0);
	}
	
	public static Element getFirstElementByClass(Element element, String styleclassname) {
		if (element == null || styleclassname == null || styleclassname.equals("")) {
			return null;
		}
		Elements elements = element.getElementsByClass(styleclassname);
		if (elements == null || elements.size() <=0) {
			return null;
		}
		return elements.get(0);
	}
	
	/**
	 * 获取Elements
	 * @param htmlDocument
	 * @param styleclassname
	 * @return
	 */
	public static Elements getElementsByClass(Document htmlDocument, String styleclassname) {
		if (htmlDocument == null || styleclassname == null || styleclassname.equals("")) {
			return null;
		}
		return htmlDocument.getElementsByClass(styleclassname);
	}
	
	/**
	 * 获取首个Element
	 * @param htmlDocument
	 * @param tag
	 * @return
	 */
	public static Element getFirstElementByTag(Document htmlDocument, String tag) {
		if (htmlDocument == null || tag == null || tag.equals("")) {
			return null;
		}
		Elements elements = htmlDocument.getElementsByTag(tag);
		if (elements == null || elements.size() <=0) {
			return null;
		}
		return elements.get(0);
	}
	
	public static Element getFirstElementByTag(Element element, String tag) {
		if (element == null || tag == null || tag.equals("")) {
			return null;
		}
		Elements elements = element.getElementsByTag(tag);
		if (elements == null || elements.size() <=0) {
			return null;
		}
		return elements.get(0);
	}
	
	public static Elements getElementsByTag(Document htmlDocument, String tag) {
		if (htmlDocument == null || tag == null || tag.equals("")) {
			return null;
		}
		return htmlDocument.getElementsByTag(tag);
	}
	
	public static Elements getElementsByTag(Element element , String tag) {
		if (element == null || tag == null || tag.equals("")) {
			return null;
		}
		return element.getElementsByTag(tag);
	}
	
	/**
	 * 获取Element
	 * @param htmlDocument
	 * @param id
	 * @return
	 */
	public static Element getElementById(Document htmlDocument, String id) {
		if (htmlDocument == null || id == null || id.equals("")) {
			return null;
		}
		return htmlDocument.getElementById(id);
	}
	
	public static void main(String[] args) {
		String url = "http://www.7160.com/meinv/14138/";
		Document document = parseDocument(url, "gb2312");
		Element banner3element = getFirstElementByClass(document, "banner3");
		Element pageclassElement = HtmlDocumentUtil.getFirstElementByClass(banner3element, "page");
		Element aElement = HtmlDocumentUtil.getFirstElementByTag(pageclassElement, "a");
		System.out.println(aElement.text());
		System.out.println(_parsePageno(document));
	}
	
	private static int _parsePageno(Document htmlDocument) {
		Element banner3Element = HtmlDocumentUtil.getFirstElementByClass(htmlDocument, "banner3");
		System.out.println("banner3elelent - > " + banner3Element);
		Element pageclassElement = HtmlDocumentUtil.getFirstElementByClass(banner3Element, "page");
		System.out.println("pageclasselement - > " + pageclassElement);
		if (pageclassElement == null) {
			return -1;
		}
		Element aElement = HtmlDocumentUtil.getFirstElementByTag(pageclassElement, "a");
		System.out.println("aelement --> " + aElement);
		if (aElement == null) {
			return -1;
		}
		String str = aElement.text();
		str = str.replaceAll("页","");
		str = str.replaceAll("共","");
		str = str.replaceAll(":","");
		if (str == null || str.equals("")) {
			return -1;
		}
		str = str.trim();
		if (str == null || str.equals("")) {
			return  -1;
		}
		return Integer.parseInt(str);
	}
}
