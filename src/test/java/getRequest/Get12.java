package getRequest;

import baseURLs.GoRestCoBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import pojoDatas.GoRestCoApiPojo;
import utulities.JsonToJava;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12 extends GoRestCoBaseURL {

    /*

        Given
            https://gorest.co.in/public/v2/users/702440
       When
			Kullanıcı GET Methodu ile Request Gönderir
		Then
			 Status Code un "200" olduğunu Assert et
 		And
 		    Response body nin bu şekilde olduğunu doğrular

    {
    "id": 702440,
    "name": "Adheesh Chopra",
    "email": "adheesh_chopra@schamberger-langosh.biz",
    "gender": "male",
    "status": "active"
    }

     */

    @Test
    public void get12(){


        //Step 1: Set URL
        //https://gorest.co.in/public/v2/users/724796
        specification.pathParams("usersPath","users",
                "idPath","724796");

        //Step 2: Set Expected Data

        String expectedData = "{\n" +
                "    \"id\": 724796,\n" +
                "    \"name\": \"Adheesh Chopra\",\n" +
                "    \"email\": \"adheesh_chopra@schamberger-langosh.biz\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        //Object Mapper
       /*
       Object Mapper ı kullanaiblmek için
       maven projelerinde pom.xml
       dependcy eklemem gerekiyor.  --->> maven repo

        */

        System.out.println("Expected Data:\n " + expectedData);


        GoRestCoApiPojo goRestCoApiPojo = JsonToJava.convertJsonToJavaObject(expectedData, GoRestCoApiPojo.class);//dataları pojo da veya
        System.out.println("goRestCoApiPojo: " + goRestCoApiPojo);


        HashMap<String,Object> expectedDataMap = JsonToJava.convertJsonToJavaObject(expectedData, HashMap.class); //map in içinde tutabiliriz.
        System.out.println("expectedDataMap: " + expectedDataMap );

        //Step 3: Send Request
        //pathParams("usersPath","users",
        //               "idPath","724790");
        Response response = given().
                spec(specification).
                when().
                get("/{usersPath}/{idPath}");

        System.out.println("RESPONSE: ");
        response.prettyPrint();

        //Step 4: Assertion:

        HashMap<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);
        System.out.println("Actual Data: " + actualDataMap);


        assertEquals(expectedDataMap.get("gender"),actualDataMap.get("gender"));
        assertEquals(expectedDataMap.get("name"),actualDataMap.get("name"));
        assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        assertEquals(expectedDataMap.get("email"),actualDataMap.get("email"));
        assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));


        /* başka bir versiyon

        Response response = given().spec(specification).when().get("/{p1}/{p2}");
        
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("name"),actualDataMap.get("name"));
        Assert.assertEquals(expectedDataMap.get("email"),actualDataMap.get("email"));
        Assert.assertEquals(expectedDataMap.get("gender"),actualDataMap.get("gender"));
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
         */
    }




}