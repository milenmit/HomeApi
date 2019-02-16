package homeAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CRUD {
	
	public static  int id;

	@Test
	public void createRecord() throws JSONException {

		String json = new JSONObject().put("Street", "Konstantin.Velichkov").put("City", "Sofia").put("zip", "454353")
				.put("phone_number", "46754365463").toString();

		RestAssured.baseURI = "http://milen.gq:3000/api/addresses/";
		Response r = given().contentType("application/json").body(json).when().post("");
		
		JsonPath jsonPathEvaluator = r.jsonPath();
		 id = jsonPathEvaluator.get("id");
		System.out.println(id);
		String body = r.getBody().asString();
		
		System.out.println(body);

	}

	public  int getId() {
		return id;
	}

	public  void setId(int id) {
		CRUD.id = id;
	}

	@Test

	public void getReccord() {
		
		System.out.println(id);
	given().when().get("http://milen.gq:3000/api/addresses/"+ id).then().statusCode(200).contentType("application/json")
				.body("id", is(id), "City", is("Sofia"));

	}

	
}
