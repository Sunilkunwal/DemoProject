package files;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest {
	
	public static void main(String[]args) {
		//TODO Auto-generated method stub
		
RestAssured.baseURI="http://localhost:8080";
//Login Senario

SessionFilter session=new SessionFilter();
String response=given().header("Content-Type","application/json").body("{ \"username\": \"synilogic.sunil\",\r\n"
		+ " \"password\": \"Kunwal1@\" }").log().all().filter(session).when().post("/rest/auth/1/session")
.then().extract().response().asString();
		
		//Add comment 
		given().pathParam("id", "10008").log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \"This Is My First Comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all()
		.assertThat().statusCode(201);
		
		
		//Add Attachment
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("id", "10008")
		.header("Content-Type","multipart/form-data")
		.multiPart("file",new File("jita.txt")).when().post("rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		
	}

}
