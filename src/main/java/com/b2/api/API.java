package com.b2.api;

import java.util.ArrayList;
import java.util.HashMap;

import com.b2.helper.APIDataReader;

public class API 
{
	private String apiName;
	private String uri;
	private QueryString[] queryString;
	private Header[] headers;
	private String body;
	private HTTPMethod method;


	public enum HTTPMethod{
		GET, 
		POST,
		PUT,
		DELETE
	}

	
	public API(String host, String apiName, HTTPMethod method) throws Exception
	{
		APIDataReader apiFileReader = new APIDataReader(apiName,method);
		this.apiName = apiName;
		this.uri = host+apiFileReader.getString("uri");
		this.queryString = readQueryString(apiFileReader);
		this.headers = readHeaders(apiFileReader); 
		this.body = apiFileReader.getString("body");
		this.method = method;
	}
	
	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public QueryString[] getQueryString() {
		return queryString;
	}

	public void setQueryString(QueryString[] queryString) {
		this.queryString = queryString;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public HTTPMethod getMethod() {
		return method;
	}

	public void setMethod(HTTPMethod method) {
		this.method = method;
	}
	
	public QueryString[] readQueryString(APIDataReader apiFileReader) throws Exception
	{
		ArrayList<QueryString> qsList = new ArrayList<>();
		QueryString qsQueryString;
		
		ArrayList<String>  keys =  apiFileReader.getKeysFromObject("querystring");
		for(String key : keys)
		{
			qsQueryString = new QueryString(key, apiFileReader.getStringFromObject("querystring", key));
			qsList.add(qsQueryString);
		}
		
		QueryString[] qsArray = new QueryString[qsList.size()];
        for (int i = 0; i < qsList.size(); i++)
        {
        	qsArray[i] = qsList.get(i);	
        }
        return qsArray;
		
	}
	
	public Header[] readHeaders(APIDataReader apiFileReader) throws Exception
	{
		ArrayList<Header> headerList = new ArrayList<>();
		Header header;
		
		ArrayList<String>  keys =  apiFileReader.getKeysFromObject("headers");
		for(String key : keys)
		{
			header = new Header(key, apiFileReader.getStringFromObject("headers", key));
			headerList.add(header);
		}
		
		Header[] headerArray = new Header[headerList.size()];
        for (int i = 0; i < headerList.size(); i++)
        {
        	headerArray[i] = headerList.get(i);	
        }
            
		return headerArray;
	}

}
