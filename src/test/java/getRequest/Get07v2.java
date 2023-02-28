package getRequest;

import baseURLs.DummyRestApiBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Get07v2 extends DummyRestApiBaseURL {

    /*
        Given
	   	    https://dummy.restapiexample.com/api/v1/employees
		When
			Kullanıcı GET Methodu ile Request Gönderir
		Then
			 Status Code un "200" olduğunu Assert et
		And
		     employee_age i 55'ten büyük olanları konsola yazdırınız.
		     8 tane olduğunu assert ediniz.
		And
            id si 17 den büyük olanları konsola yazdırınız
            7 tane olduğunu assert ediniz.
        And
            salary si 100.000'den az olanları konsola yazdırınız.
            Doris Wilder'ın bunlardan biri olduğunu assert ediniz.
     */

    @Test
    public void get07(){
        specification.pathParam("employeesPath","employees");

        Response response = given().spec(specification).when().get("/{employeesPath}");

        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        List <Integer> yasListesi = jsonPath.getList("data.findAll{it.employee_age>55}.employee_age");
        System.out.println(yasListesi);

        Collections.sort(yasListesi);
        Assert.assertEquals(8, yasListesi.size());

        List <Integer> idList = jsonPath.getList("data.findAll{it.id>17}.id");
        System.out.println(idList);

        Collections.sort(idList);
        Assert.assertEquals(7, idList.size());

        List <String> nameListForSalary = jsonPath.getList("data.findAll{it.employee_salary<100000}.employee_name");
        System.out.println(nameListForSalary);

        Collections.sort(nameListForSalary);
        Assert.assertTrue(nameListForSalary.contains("Doris Wilder"));


    }
}
