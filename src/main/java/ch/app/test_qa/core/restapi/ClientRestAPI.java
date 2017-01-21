package ch.app.test_qa.core.restapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

public class ClientRestAPI {

	private static final String URL_POST = "http://jsonplaceholder.typicode.com/posts";
	private static HttpURLConnection conn = null;

	public static JSONArray getRawResponseJSONArray(String address) {

		// To get "user" json raw response
		JSONArray jsonResponseRaw = null;
		StringBuilder jsonResponse = new StringBuilder();
		String line;

		try {

			// prepare the connection
			URL url = new URL(address);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			// check the response from the service
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			// collect the final response from the server as JSON array
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((line = br.readLine()) != null) {
				jsonResponse.append(line);
			}

			jsonResponseRaw = new JSONArray(jsonResponse.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return jsonResponseRaw;
	}

	public static boolean sendPost(int userId, String title, String body) {

		// To send a post
		try {

			// prepare the connection
			URL url = new URL(URL_POST);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			// input for the request
			String input = "{\"userId\":" + userId + ",\"title\":\"" + title + "\",\"body\":\"" + body + "\"}";

			// send the request to web service
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			// check the response from the service
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			// print the server response
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			conn.disconnect();
		}

		return true;
	}
}