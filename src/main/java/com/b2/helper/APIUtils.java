package com.b2.helper;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.b2.api.API;
import com.b2.api.Header;
import com.b2.api.QueryString;

public class APIUtils {
	
	public static API updateUri(String variable, String value, API api)
	{
		api.getUri().replace(variable, value);
		return api;
	}
	
	public static API updateBody(String variable, String value, API api)
	{
		String body = api.getBody();
		body = body.replace(variable, value);
		api.setBody(body);
		return api;
		
	}
	
	public static API updateQueryString(String variable, String value, API api)
	{
		QueryString qs;
		QueryString[] queryString = api.getQueryString();
		for(int i=0; i<queryString.length; i++)
		{
			qs = queryString[i];
			qs.setValue(qs.getValue().replace(variable, value));
		}
		return api;
		
	}

	public static API updateHeaders(String variable, String value, API api)
	{
		Header header;
		Header[] headers = api.getHeaders();
		for(int i=0; i<headers.length; i++)
		{
			header = headers[i];
			String headerValue = header.getValue();
			headerValue = headerValue.replace(variable, value);
			header.setValue(headerValue);
		}
		return api;
	}
	
	public static API removeAllHeaders(API api)
	{
		api.setHeaders(null);
		return api;
	}
	
	public static API addHeader(Header header, API api)
	{
		Header[] headers;
		
		if(api.getHeaders() == null)
		{
			headers = new Header[1];
		}
		
		headers = api.getHeaders();
		Header[] newHeaders = new Header[headers.length+1];
		int i = 0;
		
		for(Header hr : headers)
		{
			newHeaders[i] = hr;
			i++;
		}
		newHeaders[i] = header;
		api.setHeaders(newHeaders);
		
		return api;
	}
	
	public static API removeHeader(Header header, API api)
	{
		Header[] headers;
		
		if(api.getHeaders() == null)
		{
			return api;
		}
		
		headers = api.getHeaders();
		Header[] newHeaders = new Header[headers.length-1];
		int i = 0;
		
		for(Header hr : headers)
		{
			if(!header.getName().equalsIgnoreCase(hr.getName()))
			{
				newHeaders[i] = hr;
			}
			
			i++;
		}
		api.setHeaders(newHeaders);
		return api;
	}
	
	
	public static API removeAllQuerystring(API api)
	{
		api.setQueryString(null);
		return api;
	}
	
	public static API addQuerystring(QueryString qs, API api)
	{
		QueryString[] qsArray;
		
		if(api.getQueryString() == null)
		{
			qsArray = new QueryString[1];
		}
		
		qsArray = api.getQueryString();
		QueryString[] newQsArray = new QueryString[qsArray.length+1];
		int i = 0;
		
		for(QueryString qss : qsArray)
		{
			newQsArray[i] = qss;
			i++;
		}
		newQsArray[i] = qs;
		api.setQueryString(newQsArray);
		
		return api;
	}
	
	public static API removeQuerystring(QueryString qs, API api)
	{
		QueryString[] qsArray;
		
		if(api.getQueryString() == null)
		{
			qsArray = new QueryString[1];
		}
		
		qsArray = api.getQueryString();
		QueryString[] newQsArray = new QueryString[qsArray.length-1];
		int i = 0;
		
		for(QueryString qss : qsArray)
		{
			if(!qs.getName().equalsIgnoreCase(qss.getName()))
			{
				qsArray[i] = qss;
			}
			
			i++;
		}
		api.setQueryString(newQsArray);
		return api;
	}
	
	public static API setEmptyRequestBody(API api)
	{
		api.setBody("{}");
		return api;
	}
	
	public static API addNodeInRequestBody(API api, String nodePath, String key, Object value) throws Exception
	{
		
		String modfiedJSON = JSONMutator.modifyJSON(JSONMutator.ADD_NODE, nodePath, key, value, api.getBody());
		api.setBody(modfiedJSON);
		return api;
	}
	
	public static API removeNodeInRequestBody(API api, String nodePath) throws Exception
	{
		String modfiedJSON = JSONMutator.modifyJSON(JSONMutator.REMOVE_NODE, nodePath, "", "", api.getBody());
		api.setBody(modfiedJSON);
		return api;
	}
	
	public static API updateNodeInRequestBody(API api, String nodePath, String key, Object value) throws Exception
	{
		String modfiedJSON = JSONMutator.modifyJSON(JSONMutator.UPDATE_NODE, nodePath, key, value, api.getBody());
		api.setBody(modfiedJSON);
		return api;
	}
	
	//Only works for elements inside an array, not for object. For object, you need to use addANewNodeInRequestBody() 
	public static API duplicateNodeInRequestBody(API api, String nodePath)throws Exception
	{
		String modfiedJSON = JSONMutator.modifyJSON(JSONMutator.UPDATE_NODE, nodePath, "", "", api.getBody());
		api.setBody(modfiedJSON);
		return api;
	}
	
	public static String getValueFromJSONNode(String jsonBody, String nodePath)throws Exception
	{
		return JSONMutator.getValue(jsonBody, nodePath);
	}
	
	public static String getJSONRequestBody(HttpResponse response)throws Exception
	{
		HttpEntity entity =  response.getEntity();
		String jsonBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		return jsonBody;
	}
	
	
	
	
}
