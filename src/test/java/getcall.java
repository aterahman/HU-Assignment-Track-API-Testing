import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//class to get() payload
public class getcall
{
    //method to get the status code of the response payload
    @Test(priority = 1)
    public void test_statuscode()
    {
        given().
                baseUri("https://gorest.co.in/public/v1").

        when().
                get("/users").
        then().
                statusCode(200);

    }


    //method to get the response payload and to verify whether all genders are male or female
    @Test
    public void verify_response_payload()
    {
        Response response =
        given().
                baseUri("https://gorest.co.in/public/v1").

        when().
                get("/users").
        then().
                extract().response();


        JSONObject obj = new JSONObject(response.asString());

        JSONArray arr = obj.getJSONArray("data");
        for(int i =0;i<arr.length();i++)
        {
            String gender = (String) arr.getJSONObject(i).get("gender");
            Assert.assertTrue(gender.equals("male")||gender.equals("female"));

        }
    }
}
