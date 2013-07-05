package com.globalinfotek.alfresco.restclient;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class RestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultHttpClient dhc = new DefaultHttpClient();
		dhc.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM), new UsernamePasswordCredentials("han", "han"));
		HttpGet hg = new HttpGet("http://localhost:8080/share/proxy/alfresco-feed/calendar/eventList?site=hansite&format=json");
		int statusCode = 0;
		HttpResponse processResponse = null;
		String response = "";
		try {
			
			processResponse = dhc.execute(hg);
			response = IOUtils.toString(processResponse.getEntity().getContent());
			System.out.println(response);
			statusCode = processResponse.getStatusLine().getStatusCode();
			System.out.println("statusCode=" + statusCode);
			System.out.println("JSON formatted output:");
			System.out.println(new JSONObject(response).toString(2));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		dhc.getConnectionManager().shutdown();
		

	}

}
