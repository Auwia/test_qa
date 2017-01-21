package ch.app.test_qa.client_rest_api;

import java.net.MalformedURLException;
import java.util.Random;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.app.test_qa.core.restapi.Address;
import ch.app.test_qa.core.restapi.ClientRestAPI;
import ch.app.test_qa.core.restapi.Geo;
import ch.app.test_qa.core.restapi.ParserJSON;
import ch.app.test_qa.core.restapi.Post;
import ch.app.test_qa.core.restapi.User;

public class TestClientRestAPI {

	private static final String URL_GET_USERS = "https://jsonplaceholder.typicode.com/users";
	private static final String URL_GET_POSTS = "https://jsonplaceholder.typicode.com/posts?userId=";

	private static final User user = new User();
	private static final Address address = new Address();
	private static final Geo geo = new Geo();
	private static final Post post = new Post();

	private JSONArray jsonResponseRaw;
	private ParserJSON parser;

	@Before
	public void beforeEachTest() throws MalformedURLException {
		System.out.println("START TEST CLIENT REST API");
	}

	@After
	public void afterEachTest() {
		System.out.println("END TEST CLIENT REST API");
	}

	@Test
	public void testA() {

		// invoke webservice to get user information as raw JSONArray (without
		// any parsing)
		jsonResponseRaw = ClientRestAPI.getRawResponseJSONArray(URL_GET_USERS);

		// Select a random number into the range of json response userId
		Random r = new Random();
		int rndNum = r.nextInt(jsonResponseRaw.length() - 1) + 1;

		// Parse raw json response
		parser = new ParserJSON(jsonResponseRaw.getJSONObject(rndNum));

		// initialize java "user" objects
		setUserObj(parser);

		// formal check for email address
		Assert.assertEquals(user.isEmailValid(user.getEmail()), true);

		// Report on console
		System.out.println("UserId: " + user.getUserId());
		System.out.println("email: " + user.getEmail() + " |<->| valid: " + user.isEmailValid(user.getEmail()));
		System.out.println("-----------------------------");
		System.out.println("Address: ");
		System.out.println("street: " + address.getStreet());
		System.out.println("suite: " + address.getSuite());
		System.out.println("city: " + address.getCity());
		System.out.println("zip code: " + address.getZipCode());
		System.out.println("-----------------------------");
		System.out.println("Geo Localization: ");
		System.out.println("latitude: " + geo.getLat());
		System.out.println("longitude: " + geo.getLng());
		System.out.println("-----------------------------");
	}

	@Test
	public void testB() {
		// invoke webservice to get Posts information as raw JSONArray (without
		// any parsing)
		jsonResponseRaw = ClientRestAPI.getRawResponseJSONArray(URL_GET_POSTS + user.getUserId());

		for (int i = 0; i < jsonResponseRaw.length(); i++) {

			// Parse raw json response for each post
			parser = new ParserJSON(jsonResponseRaw.getJSONObject(i));

			// Report on console and formal check on title and body post
			System.out.println(
					"Post with ID: " + parser.getId() + " ----> Title is VALID? " + post.isValidTitle(parser.getTitle())
							+ " ----> Body is VALID? " + post.isValidBody(parser.getBody()));

			Assert.assertNotNull(parser.getTitle());
			Assert.assertNotNull(parser.getBody());

		}

		// Separate report parts
		System.out.println("-----------------------------");
	}

	@Test
	public void testC() {
		// Publish a new post invoking post web service with fixed testing data
		boolean result = ClientRestAPI.sendPost(user.getUserId(), "Test Title", "Test Body");

		// check if the response is true as positive scenario
		Assert.assertTrue(result);
	}

	private void setUserObj(ParserJSON parser) {
		user.setUserId(parser.getUserId());
		user.setEmail(parser.getEmail());
		address.setStreet(parser.getStreet());
		address.setSuite(parser.getSuite());
		address.setCity(parser.getCity());
		address.setZipCode(parser.getZipCode());
		geo.setLat(parser.getLat());
		geo.setLng(parser.getLng());
	}

}
