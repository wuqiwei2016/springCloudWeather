package com.htht.weather.springCloudWeather.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	private HttpUtil() {}  
	
	private static final HttpUtil single = new HttpUtil();  
	
	public static HttpUtil getInstance() {  
		return single;  
    }
	
	public String doPost(String url,Map<String, String> params) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000); 
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(String key : params.keySet()){
			NameValuePair nvp = new BasicNameValuePair(key, params.get(key));
			nvps.add(nvp);
		}
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpEntity = response.getEntity();
		String html = "";
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null){
			html = EntityUtils.toString(httpEntity, "UTF-8");
		}
		return html;
	}
	
	
	public String doGet(String url) throws ClientProtocolException, IOException{
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000); 
		HttpGet httpGet = new HttpGet(url);
		HttpEntity httpEntity = null;
		String html = null;
		HttpResponse response = null;
		response= httpClient.execute(httpGet);
		httpEntity = response.getEntity();
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null){
			html = EntityUtils.toString(httpEntity, "UTF-8");
		}
		httpGet.abort();
		return html;
	}
	
	public String doPostJson(String url,String params) throws Exception{
		String result ="";
		HttpClient httpClient = new DefaultHttpClient();
		HttpEntity httpEntity = null;
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000); 
		StringEntity s = new StringEntity(params,"utf-8");
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json; charset=utf-8");
		HttpPost post = new HttpPost(url);
		post.setEntity(s);
		HttpResponse res = httpClient.execute(post);
		httpEntity = res.getEntity();
		if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null){
			result = EntityUtils.toString(httpEntity, "UTF-8");
		}
		return result;
	}
}
