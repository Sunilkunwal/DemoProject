import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
// validate if Add Place API is working as expected 
		//Add place-> Update place with New Address -> Get Place to validate if New address is present in response 
		
		//given - all input details
		//when - Submit the API resorce, http method
		//Then - validate the response
		//content of the file to String-> content of file can convert into Byte->Byte data to String 
		
	
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("content-Type","application/json")
		//.body(payload.AddPlace())
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\pc\\Downloads\\addPlace.json"))))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);  //for parsing Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update place
		String newAddress = "123 kota Raj";
		
		
		given().log().all().queryParam("key", "qaclick123").header("content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
	
		
		//Get Place 
		
		String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js1 =ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		//Cucumber Junit, TestNG
	}
}
		
		
		

		
		
	
