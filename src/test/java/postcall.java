import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

//class to get() payload
public class postcall
{
    @Test(priority = 1)
    public void test_statuscode()throws FileNotFoundException, IOException
    {
        String temp_token = "1e3fbfc04d510423f16db6b1976fb8d49a339e4e486ef5006ebe118b552a2a32";
        JSONArray arr;

        excelcall e = new excelcall();
        arr = e.excel();
        JSONObject obj = arr.getJSONObject(0);

        given().
                baseUri("https://gorest.co.in/public/v1").
                header("Authorization","Bearer"+ temp_token).
                body(obj).
                header("Content-type","applications/java").
        when().
                post("/users").
        then().
                statusCode(201);
    }

    @Test(priority = 2)
    public void get_response_payload()
    {
        given().
                baseUri("https://gorest.co.in/public/v1").


                when().
                post("/users").
                then().
                extract().response();

    }
}
