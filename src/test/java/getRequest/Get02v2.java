package getRequest;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class Get02v2 {



        /*
      Given
          https://restful-booker.herokuapp.com/booking/78100
      When
          Kullanıcı GET Methodu ile Request Gönderir
      Then
          Status Code un "404" olduğunu Assert et
      And
          Response Body nin "Not Found" olduğunu assert et
      And
          Headers dan Via nın "1.1 vegur" olduğunu assert et.
      And
          Response body nin "Clarusway" yazmadığını assert et
      And
          Status Line "HTTP/1.1 404 Not Found" olduğunu assert et.

       */


    @Test
    public void test01() {
        String URL = "https://jsonplaceholder.typicode.com/todos/2";
        Response response = given().when().get(URL);

        response.prettyPrint();
        response.then().assertThat().statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("quis ut nam facilis et officia qui"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(1));

        Assert.assertTrue(response.asString().contains("\"completed\": false"));
    }
}