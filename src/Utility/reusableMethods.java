package Utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * @author Nihar Jhingade
 * @Date 10/14/2018
 */
public class reusableMethods {

    public static JsonPath rawToJson(Response res){
        String response = res.asString();
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }

}
