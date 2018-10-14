import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author Nihar Jhingade
 * @Date 10/7/2018
 */
public class addAndDelete {

    Properties prop = new Properties();

    @BeforeTest
    public void setup()throws IOException {
        FileInputStream fileInput = new FileInputStream("C:\\Me\\Study Materials\\Github Related\\NiharMyth\\RestAssured\\filesFolder\\config.properties");
        prop.load(fileInput);
    }

    @Test
    public void addAndDelete_(){

        // Task 1 - Grab the response
        String keyValue = prop.getProperty("key");
        RestAssured.baseURI =  prop.getProperty("url");
        Response response = given().

                queryParam("key",keyValue).
                body(payLoad.getPostData()).
                when().
                post(resources.placePostData()).
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).
                extract().
                response();

        String responseString = response.asString();
        System.out.println(responseString);

        //Task 2 - Grab the place-id from the response
        JsonPath json = new JsonPath(responseString);
        String placeId = json.get("place_id");
        System.out.println(placeId);

        //Task 3 - Delete the request using place_id
        String placeIdString = "{\"place_id\":\""+placeId+"\"}";
        given().
                queryParam("key",keyValue).

                body(placeIdString).
                when().
                post("/maps/api/place/delete/json").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));

    }
}
