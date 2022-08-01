package com.b2.api.runner;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Cookie;

import com.b2.api.API;
import com.b2.api.API.HTTPMethod;
import com.b2.api.Header;
import com.b2.api.QueryString;
import com.b2.driver.B2UIDriver;
import com.b2.helper.APIUtils;
import com.b2.helper.TestDataReader;
import com.b2.helper.TestEnvironment;

public class APIRunner {
	
	public static HttpResponse executeGet(API api) throws Exception
	{
		
		int timeout = 3;
		RequestConfig config = RequestConfig.custom().
		  setConnectTimeout(timeout * 1000).
		  setConnectionRequestTimeout(timeout * 1000).
		  setSocketTimeout(timeout * 1000).build();
		CloseableHttpClient httpclient = HttpClientBuilder.create()
		  .setDefaultRequestConfig(config).build();

		closeIdleConnections(httpclient);
		HttpGet request = new HttpGet(api.getUri());
		
		if(api.getQueryString() != null && api.getQueryString().length  > 0)
		{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList();
			for(QueryString qs : api.getQueryString())
			{
				nameValuePairs.add(new BasicNameValuePair(qs.getName(), qs.getValue()));
				
			}
			URI uri = new URIBuilder(request.getURI())
				      .addParameters(nameValuePairs)
				      .build();
			((HttpRequestBase) request).setURI(uri);
			
		}
		
		if(api.getHeaders() != null && api.getHeaders().length  > 0)
		{
			for(Header header : api.getHeaders())
			{
				request.setHeader(header.getName(), header.getValue());
			}	
		}
		CloseableHttpResponse response = httpclient.execute(request);
		return response;
		
	}
	
	private static void closeIdleConnections(HttpClient httpclient)
	{
		httpclient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
	}
	
	public static HttpResponse executePost(API api) throws Exception
	{
		
		int timeout = 3;
		RequestConfig config = RequestConfig.custom().
		  setConnectTimeout(timeout * 1000).
		  setConnectionRequestTimeout(timeout * 1000).
		  setSocketTimeout(timeout * 1000).build();
		CloseableHttpClient httpclient = HttpClientBuilder.create()
		  .setDefaultRequestConfig(config).build();

		closeIdleConnections(httpclient);
		
		HttpPost request = new HttpPost(api.getUri());
		
		if(api.getQueryString() != null && api.getQueryString().length  > 0)
		{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList();
			for(QueryString qs : api.getQueryString())
			{
				nameValuePairs.add(new BasicNameValuePair(qs.getName(), qs.getValue()));
				
			}
			URI uri = new URIBuilder(request.getURI())
				      .addParameters(nameValuePairs)
				      .build();
			((HttpRequestBase) request).setURI(uri);
			
		}
		
		if(api.getHeaders() != null && api.getHeaders().length  > 0)
		{
			for(Header header : api.getHeaders())
			{
				request.setHeader(header.getName(), header.getValue());
			}
		}
		
		StringEntity entity = new StringEntity(api.getBody(),"application/json","UTF-8");
		request.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(request);
		return response;

	}
	
	
	
	public static HttpResponse executePut(API api) throws Exception
	{
		int timeout = 3;
		RequestConfig config = RequestConfig.custom().
		  setConnectTimeout(timeout * 1000).
		  setConnectionRequestTimeout(timeout * 1000).
		  setSocketTimeout(timeout * 1000).build();
		CloseableHttpClient httpclient = HttpClientBuilder.create()
		  .setDefaultRequestConfig(config).build();
		
		closeIdleConnections(httpclient);
		
		HttpPut request = new HttpPut(api.getUri());
		
		if(api.getQueryString() != null && api.getQueryString().length  > 0)
		{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList();
			for(QueryString qs : api.getQueryString())
			{
				nameValuePairs.add(new BasicNameValuePair(qs.getName(), qs.getValue()));
				
			}
			URI uri = new URIBuilder(request.getURI())
				      .addParameters(nameValuePairs)
				      .build();
			((HttpRequestBase) request).setURI(uri);
			
		}
		
		if(api.getHeaders() != null && api.getHeaders().length  > 0)
		{
			for(Header header : api.getHeaders())
			{
				request.setHeader(header.getName(), header.getValue());
			}
		}
		
		StringEntity entity = new StringEntity(api.getBody());
		request.setEntity(entity);
		
		CloseableHttpResponse response = httpclient.execute(request);
		return response;

	}
	
	public static HttpResponse executeDelete(API api) throws Exception
	{
		int timeout = 3;
		RequestConfig config = RequestConfig.custom().
		  setConnectTimeout(timeout * 1000).
		  setConnectionRequestTimeout(timeout * 1000).
		  setSocketTimeout(timeout * 1000).build();
		CloseableHttpClient httpclient = HttpClientBuilder.create()
		  .setDefaultRequestConfig(config).build();

		closeIdleConnections(httpclient);
		
		HttpDelete request = new HttpDelete(api.getUri());
		
		if(api.getQueryString() != null && api.getQueryString().length  > 0)
		{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList();
			for(QueryString qs : api.getQueryString())
			{
				nameValuePairs.add(new BasicNameValuePair(qs.getName(), qs.getValue()));
				
			}
			URI uri = new URIBuilder(request.getURI())
				      .addParameters(nameValuePairs)
				      .build();
			((HttpRequestBase) request).setURI(uri);
			
		}
		
		if(api.getHeaders() != null && api.getHeaders().length  > 0)
		{
			for(Header header : api.getHeaders())
			{
				request.setHeader(header.getName(), header.getValue());
			}
		}		
		CloseableHttpResponse response = httpclient.execute(request);
		
		return response;
	}
	
	public static void main(String[] args) throws Exception
	{
		TestDataReader tdReader = new TestDataReader("qa");
		String mailchimpHost = tdReader.getString("mailchimp_uri");
		
		API mailchimpAPI = new API(mailchimpHost, "insyncwebhook", HTTPMethod.POST);
		
		Cookie cookie = B2UIDriver.getCookiesNamed("b2Token");
		APIUtils.updateHeaders("<cookie>", cookie.getName()+"="+cookie.getValue(), mailchimpAPI);
		APIUtils.updateQueryString("<tenant_id>", tdReader.getString("tenant_id"), mailchimpAPI);
		HttpResponse response = APIRunner.executePost(mailchimpAPI);
		System.out.println("Execution completed - Status is " + response.getStatusLine());
		
	}
	
	
}
