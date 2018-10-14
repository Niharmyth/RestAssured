import Utility.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * @author Nihar Jhingade
 * @Date 10/14/2018
 */
public class FetchNamesFromResources {


    @Test
    public void fetchNamesFromResources(){

        RestAssured.baseURI = "https://maps.googleapis.com/";

        Response res = given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key","AIzaSyBcfysrI3YFUyQrYvAlgq2vNER7LEq0-Hs").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
                extract().response();

        JsonPath js = reusableMethods.rawToJson(res);
        int count = js.get("results.size()");

        for(int i = 0; i < count; i++){
            String name = js.get("results["+i+"].name");
            System.out.println(name);

            String vicinity = js.get("results["+i+"].vicinity");
            System.out.println(vicinity);

            System.out.println("-----------------------------------");
        }
        System.out.println(count);

        //JsonPath json = new JsonPath();



    }
}
