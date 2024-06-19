package com.restassured.sample;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Testone {

	@Test
	public String getUserById() {
		
		try {	
		
			  RestAssured.baseURI = "https://reqres.in/";	
			  
			  String basePath = "api/users";
			  
			  RequestSpecification requestSpecification = RestAssured.given();
			  
			  Response response = requestSpecification.get(basePath);
			  
			  JsonPath jsonPath = response.jsonPath();
			  
			  List<Map<String, Object>> dataList = jsonPath.getList("data");
			  
			  boolean idFound = false;
			  
			  for(Map<String, Object> user : dataList) {
				  
				  if(user.get("id").equals(182)) {
					  
					  System.out.println("User with ID 182 : " + user);
					  
					  idFound = true;
					  
					   break;
				  }
			  }
			  
		        if (!idFound) {
		            System.out.println("User with ID 182 not found in the response.");
		        }
		        
		        response.then().statusCode(200);
			
		} catch (Exception e) {
		   
			System.out.println(e.getMessage());
		}
		
		return "Success";
	}
	
	
	@Test
	public String getAllUsers() {
		
		try {
			
			RestAssured.baseURI = "https://reqres.in/";
			
			String basePath = "api/users";
			
			RequestSpecification requestSpecification = RestAssured.given();
			
			Response response = requestSpecification.get(basePath);
			
			JsonPath jsonPath = response.jsonPath();
			
			List<Map<String, Object>> allUsers = jsonPath.getList("data");
			
			 allUsers.forEach(users -> {
				
				 System.out.println("ID: " + users.get("id"));
		         System.out.println("Email: " + users.get("email"));
		         System.out.println("First Name: " + users.get("first_name"));
		         System.out.println("Last Name: " + users.get("last_name"));
		         System.out.println("Avatar: " + users.get("avatar"));
		         System.out.println("----------------------------");
			 });
			 
			 response.then().statusCode(200);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	@Test
	public String createUsers() {
		
		try {
			
			 RestAssured.baseURI = "https://reqres.in/";
	          
			 String basePath = "api/users";
	         
			 RequestSpecification request = RestAssured.given();
	         
			 JSONObject requestParams = new JSONObject();// JSON Object Creation
	         
			 requestParams.put("name", "John"); // Adding the information as key-value pair in the JSON
	         
			 requestParams.put("job", "tester");
	         
			 request.body(requestParams);
	         
			 request.header("Content-Type", "application/json");
	         
			 Response response = request.post(basePath); // here we are hitting the uri
	         
			 System.out.println("\n Status Code: " + response.getStatusCode()); // Response status code is printed here 
	         
			 System.out.println("---> Response JSON Body: " + response.getBody().asString());
			  
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	@Test
	public void deleteUsers() {
		
		try {
			
			RestAssured.baseURI = "https://reqres.in/api/users";
			
			String deleteById = "182";
			
			Response response = RestAssured.given().delete("/users/{id}", deleteById);
			
			response.then().statusCode(200);		
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void main(String [] args) {
		
		Testone testOne = new Testone();
		
		testOne.getUserById();
	}
	
}


