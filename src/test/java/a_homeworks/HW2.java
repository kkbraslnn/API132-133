package a_homeworks;

import base_urls.Reqres;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.junit.Assert.assertEquals;

public class HW2 extends Reqres {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */

@Test
    public void HW2(){
    //set the url
   // String url = "https://reqres.in/api/users/23";
    spec.pathParams("first","users","second",23);
    //
    //send the request and get the response
    Response response = given(spec).get("{first}/{second}");
    response.prettyPrint();
    //do assertion
    response.then().
            statusCode(404).
            statusLine(" HTTP/1.1 404 Not Found").
            header("Server","cloudflare").
            body("",anEmptyMap());

    JsonPath jsonPath=response.jsonPath();
    System.out.println("jsonPath = " + jsonPath);

    assertEquals(404,response.statusCode());
    assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
    assertEquals("cloudflare",response.getHeader("Server"));
    assertEquals("{}",jsonPath.get());

 }

}
