
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * @author Nihar Jhingade
 * @Date 10/7/2018
 */
public class postTest {

    @Test
    public void PostTest_(){

        RestAssured.baseURI = "http://216.10.245.166";

        given().
                queryParam("key","qaclick123").
                body("{\n" +
                        "\"location\":{" +
                        "\"lat\": -38.383494," +
                        "\"lng\": 33.427362" +
                        "},\n" +
                        "\"accuracy\":50,\n" +
                        "\"name\":\"Frontline house\"," +
                        "\"phone_number\":\"(+91) 9833937\"," +
                        "\"address\":\"941 singapore\"," +
                        "\"type\": [\"shoe park\", \"shop\"]," +
                        "\"website\": \"http://google.com\"," +
                        "\"language\":\"French-IN\"" +
                        "}").
                when().
                post("/maps/api/place/add/json").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK"));
        }
}
