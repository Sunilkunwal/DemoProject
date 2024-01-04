package files;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test
	public void addBook()
	{
	RestAssured.baseURI="http://216.10.245.166";
	String response= given().header("Content-Type", "application/json").
	body(payload.Addbook("adsfs","6464")).when().post("Library/Addbook.php")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js= ReUsableMethods.rawToJson(response);
	String id =js.get("ID");
	System.out.println(id);
	
	
	
	
	
	
	
	// Create a place =response (place id)
	
		//delete place = (Request - Place id)
	}

}
