package com.restassured.sample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Testone {

	@Test
	public String getUserById() {
		
		try {	
		
			  RestAssured.baseURI = "https://reqres.in/api";	
			  
			  String basePath = "users?page=2";
			  
			  RequestSpecification requestSpecification = RestAssured.given();
		        
			  Response response = requestSpecification.get(basePath);
			  
			  JsonPath jsonPath = response.jsonPath();
			  
			  List<Map<String, Object>> dataList = jsonPath.getList("data");
			
			 boolean idFound = false;
			
			  for(Map<String, Object> user : dataList) {
				  
				  if(user.get("id").equals(7)) {
					  
					  System.out.println("User with ID 7: " + user);
					  
					  idFound = true;
					  
					  break;				  
				  }
				  
				  if(!idFound) {
						 
						 System.out.println("User with ID 7 : " + user);
					 }
			  }
			  
			  int statusCode = response.getStatusCode();
			     
			     if(statusCode == 200) {
			    	 
			    	 System.out.println("Test case passed");
			     
			     } else {
			    	 
			    	 System.out.println("The invalid statusCode is : " + response.getStatusCode());
			     }  
			  
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
			 
           int statusCode = response.getStatusCode();
		     
		     if(statusCode == 200) {
		    	 
		    	 System.out.println("Test case passed");
		     
		     } else {
		    	 
		    	 System.out.println("The invalid statusCode is : " + response.getStatusCode());
		     }
		     
		     System.out.println("---> Response JSON Body: " + response.getBody().asString());
			
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
	         
			 Map<String, Object> map = new HashedMap<String, Object>();
			 
			 map.put("first_name", "John");
			 
			 map.put("last_name", "Wick");
			 
			 RequestSpecification requestSpecification = RestAssured.given()
					 .contentType(ContentType.JSON).body(map);
	         
			 Response response = requestSpecification.post(basePath); // here we are hitting the uri
	         
			 System.out.println("\n Status Code: " + response.getStatusCode()); // Response status code is printed here 
	         
			 System.out.println("---> Response JSON Body: " + response.getBody().asString());
			 
           int statusCode = response.getStatusCode();
		     
		     if(statusCode == 201) {
		    	 
		    	 System.out.println("Test case passed");
		     
		     } else {
		    	 
		    	 System.out.println("The invalid statusCode is : " + response.getStatusCode());
		     }
			  
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	
	public String updateById() {
		
		try {
			
			RestAssured.baseURI = "https://reqres.in/api";
			
			String basePath = "/users/10";
			
			Map<String, Object> map = new HashedMap<String, Object>();
			
			map.put("first_name", "Moahmmed Imran");
			
			map.put("last_name", "Khan");
			
			RequestSpecification requestSpecification = RestAssured.given()
					.contentType(ContentType.JSON).body(map);
			
			Response response = requestSpecification.put(basePath);
			
            System.out.println("\n Status Code: " + response.getStatusCode()); // Response status code is printed here 
	         
			System.out.println("---> Response JSON Body: " + response.getBody().asString());
			 
           int statusCode = response.getStatusCode();
		     
		     if(statusCode == 200) {
		    	 
		    	 System.out.println("Test case passed");
		     
		     } else {
		    	 
		    	 System.out.println("The invalid statusCode is : " + response.getStatusCode());
		     }
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	@Test
	public void deleteUserById() {
		
		try {
			
			RestAssured.baseURI = "https://reqres.in/api/users";
			
			String basePath = "7";
			
			RequestSpecification requestSpecification = RestAssured.given();
			
			Response response = requestSpecification.delete(basePath);
			
			 System.out.println("\n Status Code: " + response.getStatusCode()); // Response status code is printed here 
	         
				System.out.println("---> Response JSON Body: " + response.getBody().asString());
				 
	           int statusCode = response.getStatusCode();
			     
			     if(statusCode == 204) {
			    	 
			    	 System.out.println("Test case passed");
			     
			     } else {
			    	 
			    	 System.out.println("The invalid statusCode is : " + response.getStatusCode());
			     }
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void main(String [] args) {
		
		Testone testOne = new Testone();
		
		testOne.getAllUsers();
	}
	
}


