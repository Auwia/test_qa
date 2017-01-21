package ch.app.test_qa.core.restapi;

import org.json.JSONObject;

public class ParserJSON {

	private final JSONObject obj;

	public ParserJSON(JSONObject obj) {
		this.obj = obj;
	}

	private JSONObject getAddress() {
		return obj.getJSONObject("address");
	}

	private JSONObject getGeo() {
		return getAddress().getJSONObject("geo");
	}
	
	public int getUserId() {
		return obj.getInt("id");
	}

	public String getEmail() {
		return obj.getString("email");
	}

	public String getStreet() {
		return getAddress().getString("street");
	}

	public String getSuite() {
		return getAddress().getString("suite");
	}

	public String getCity() {
		return getAddress().getString("city");
	}

	public String getZipCode() {
		return getAddress().getString("zipcode");
	}

	public Double getLat() {
		return getGeo().getDouble("lat");
	}

	public Double getLng() {
		return getGeo().getDouble("lng");
	}
	
	public int getId(){
		return obj.getInt("id");
	}
	
	public String getTitle(){
		return obj.getString("title");
	}
	
	public String getBody(){
		return obj.getString("body");
	}

}
