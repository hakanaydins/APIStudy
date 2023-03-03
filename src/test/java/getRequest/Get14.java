package getRequest;

import baseURLs.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;
import utulities.JsonToJava;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Get14 extends JsonPlaceHolderBaseURL {


    /*

        Given
            https://jsonplaceholder.typicode.com/todos/198
       When
			Kullanıcı GET Methodu ile Request Gönderir
		Then
			 Status Code un "200" olduğunu Assert et
 		And
 		    Response body nin bu şekilde olduğunu doğrular

                 { "userId": 10,
				    "id": 198,
				    "title": "quis eius est sint explicabo",
				    "completed": true
				  }

     */

    @Test
    public void get14(){

        //Step 1: Set URL
        //https://jsonplaceholder.typicode.com/todos/198
        specification.pathParams("todosPath","todos",
                "idPath","198");


        //Step 2: Set Expected Data

        String expectedData = "{\n" +
                "    \"userId\": 10,\n" +
                "    \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "    \"completed\": true\n" +
                "}";


        System.out.println("ExpectedDataString: " + expectedData);

        HashMap<String,Object> expectedDataMap = JsonToJava.convertJsonToJavaObject(expectedData,HashMap.class);
        System.out.println("EXPECTED DATA MAP: " + expectedDataMap);

        //HOMEWORK:   expected data yı JSONPLACEHOLDERTESTDATA classın da bir tane metot create ediniz.

        /*
         JsonPlaceHolderBaseURL jsonPlaceHolderBaseURL = new JsonPlaceHolderBaseURL();

        String ex = jsonPlaceHolderBaseURL.createEdilen(userId);
         */


        //Step 3: Send a request
        Response response = given().
                spec(specification).
                when().
                get("/{todosPath}/{idPath}");

        System.out.println("RESPONSE: ");
        response.prettyPrint();


        // Step 4: Assertion

        //Response

        //HashMap<String,Object> actualDataMap =  response.as(HashMap.class);   // ---> NOme Problemooo


        //  JsonPath jsonPath = response.jsonPath(); /// ----> NOme Problemooo

        //  response.then().assertThat().body(""); /// ---> Nome Problemo


        HashMap<String,Object> actualDataMapJSONToJAVA = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);

        System.out.println("actualDataMapJSONToJAVA: " + actualDataMapJSONToJAVA);

        /*
        ASSERTION HOMEWORK
         */

      Assert.assertEquals(expectedDataMap.get("UserId"),actualDataMapJSONToJAVA.get("UserId"));
      Assert.assertEquals(expectedDataMap.get("id"),actualDataMapJSONToJAVA.get("id"));
      Assert.assertEquals(expectedDataMap.get("completed"),actualDataMapJSONToJAVA.get("completed"));
      Assert.assertEquals(expectedDataMap.get("title"),actualDataMapJSONToJAVA.get("title"));

      /* 2.yol


        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
        HashMap<String,Object>jsonmap=jsonPlaceHolderTestData.setUpDataJson();
        System.out.println("jsonmap = " + jsonmap);
        for (String key: actualDataMapJSONToJAVA.keySet()){
            Assert.assertEquals(jsonmap.get(key),actualDataMapJSONToJAVA.get(key));
        }

       */

    }
}
