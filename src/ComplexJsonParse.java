import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		//TODO Auto-generated method stub
		
		
		JsonPath js=new JsonPath(payload.CoursePrice());
		

//Print no of courses returned by API
		
int count= js.getInt("courses.size()");
System.out.println(count);

//print Purchase Amount

int totalAmount= js.getInt("dashboard.purchaseAmount");
System.out.println(totalAmount);

//print Title of the first courses 

String titleFirstCourse= js.get("courses[0].title");
System.out.println(titleFirstCourse);

//print All course titles and their respective price 

for(int i=0;i<count;i++)
{
	String courseTitles=js.get("courses["+i+"].title");
	System.out.println(courseTitles);
	System.out.println(js.get("courses["+i+"].price").toString());
	
}



//Print no of copies sold by RPA Course

System.out.println("Print no of copies slod by RPA Courses");

for(int i=0;i<count;i++)
{
	String courseTitles=js.get("courses["+i+"].title");
	if(courseTitles.equalsIgnoreCase("RPA"))
	{
		int copies=js.getInt("courses["+i+"].copies");
		System.out.println(copies);
		break;
	}
	
	
}





	}

}
