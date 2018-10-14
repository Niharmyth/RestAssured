import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * @author Nihar Jhingade
 * @Date 10/5/2018
 */
public class basic {

    @Test
    public void TEST(){

        // Base URL https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyCOLDeP4czNzEaqGur-HjlBJRN_QR8-Zy8
        RestAssured.baseURI = "https://maps.googleapis.com/";

        given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key","AIzaSyBcfysrI3YFUyQrYvAlgq2vNER7LEq0-Hs").log().all().
                when().
                get("/maps/api/place/nearbysearch/json").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .and().
                //body("results[0].geometry.location.lat", equalTo("-33.8688197"));
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")). and().
                header("content-type",equalTo("application/json; charset=UTF-8"));
    }
}
