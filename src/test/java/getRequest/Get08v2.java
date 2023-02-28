package getRequest;

import baseURLs.SwapiApiBaseURL;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Get08v2 extends SwapiApiBaseURL {


    /*

Given
            https://swapi.dev/api/vehicles/4
    When
        Kullanıcı GET Methodu ile Request Gönderir
    Then
         Status Code un "200" olduğunu Assert et
    And
         Response body nin bu şekilde olduğunu doğrular
{
"name": "Sand Crawler",
"model": "Digger Crawler",
"manufacturer": "Corellia Mining Corporation",
"cost_in_credits": "150000",
"length": "36.8 ",
"max_atmosphering_speed": "30",
"crew": "46",
"passengers": "30",
"cargo_capacity": "50000",
"consumables": "2 months",
"vehicle_class": "wheeled",
"pilots": [],
"films": [
    "https://swapi.dev/api/films/1/",
    "https://swapi.dev/api/films/5/"
],
"created": "2014-12-10T15:36:25.724000Z",
"edited": "2014-12-20T21:30:21.661000Z",
"url": "https://swapi.dev/api/vehicles/4/"
}

 */

    @Test
    public void get08() {
            RestAssured.baseURI = "https://swapi.dev/api";
            List<String> pilotsList = new ArrayList<>();
            Map<String, Object> expectedDetails = new HashMap<>();
            expectedDetails.put("name", "Sand Crawler");
            expectedDetails.put("model", "Digger Crawler");
            expectedDetails.put("manufacturer", "Corellia Mining Corporation");
            expectedDetails.put("cost_in_credits", "150000");
            expectedDetails.put("length", "36.8 ");
            expectedDetails.put("max_atmosphering_speed", "30");
            expectedDetails.put("crew", "46");
            expectedDetails.put("passengers", "30");
            expectedDetails.put("cargo_capacity", "50000");
            expectedDetails.put("consumables", "2 months");
            expectedDetails.put("vehicle_class", "wheeled");
            expectedDetails.put("pilots", pilotsList);
            expectedDetails.put("films[0]", "https://swapi.dev/api/films/1/");
            expectedDetails.put("films[1]", "https://swapi.dev/api/films/5/");
            expectedDetails.put("created", "2014-12-10T15:36:25.724000Z");

            expectedDetails.put("edited", "2014-12-20T21:30:21.661000Z");
            expectedDetails.put("url", "https://swapi.dev/api/vehicles/4/");

            String response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/vehicles/4")
                    .asString();

            JsonPath jsonPath = new JsonPath(response);

            for (Map.Entry<String, Object> entry : expectedDetails.entrySet()) {
                Object expectedValue = entry.getValue();
                Object actualValue = jsonPath.get(entry.getKey());

                if (expectedValue instanceof List) {
                    List<?> expectedList = (List<?>) expectedValue;
                    List<?> actualList = jsonPath.getList(entry.getKey());
                    org.hamcrest.MatcherAssert.assertThat(actualList, equalTo(expectedList));
                } else {
                    org.hamcrest.MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
                }
            }
        }
    }


