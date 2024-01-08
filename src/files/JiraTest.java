package files;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class JiraTest {
	
	public static void main(String[]args) {
		//TODO Auto-generated method stub
		
RestAssured.baseURI="http://localhost:8080";
//Login Senario

String response=given().header("Content-Type","application/json").body("{ \"username\": \"synilogic.sunil\",\r\n"
		+ " \"password\": \"Kunwal1@\" }").log().all().when().post("/rest/auth/1/session")
.then().extract().response().asString();
		
		
		given().pathParam("id", "10008").log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \"This Is My First Comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").when().post("/rest/api/2/issue/{id}/comment")
	}

}
