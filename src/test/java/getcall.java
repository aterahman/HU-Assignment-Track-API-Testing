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
    JSONObject obj;
    JSONArray arr;

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

    //method to get the response payload and to define global variables JSONObject and JSONArray
    @Test(priority = 2)
    public void get_response_payload()
    {
        Response response =
                given().
                        baseUri("https://gorest.co.in/public/v1").

                        when().
                        get("/users").
                        then().
                        extract().response();


        obj = new JSONObject(response.asString());

         arr = obj.getJSONArray("data");

    }


    //method to verify whether all genders in response are male or female
    @Test(priority = 3)
    public void verify_response_payload_gender()
    {

        for(int i =0;i<arr.length();i++)
        {
            String gender = (String) arr.getJSONObject(i).get("gender");
            Assert.assertTrue(gender.equals("male")||gender.equals("female"));

        }
    }


    //method to verify if the ids in the payload are unique
    @Test(priority = 4)
    public void verify_response_payload_id()
    {

        for(int i =0;i<arr.length();i++)
        {
            int id1 = (int) arr.getJSONObject(i).get("id");
            for(int j =i+1;j<arr.length();j++)
            {
                int id2 = (int) arr.getJSONObject(j).get("id");
                Assert.assertFalse(id1==id2);
            }

        }
    }
}
