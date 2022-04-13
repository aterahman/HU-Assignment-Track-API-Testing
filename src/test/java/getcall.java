import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

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
                log().all().

        when().
                get("/users").
        then().
                log().all().
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
            //stores the gender at location i in the array
            String gender = (String) arr.getJSONObject(i).get("gender");

            //checks if the required condition is met
            Assert.assertTrue(gender.equals("male")||gender.equals("female"));

        }
    }

    //method to check if atleast 2 emails have .biz
    @Test(priority = 4)
    public void verify_response_payload_emailextension()
    {
        int c=0;
        for(int i = 0;i<arr.length();i++)
        {
            //stores the email at location i in the array
            String email = (String) arr.getJSONObject(i).get("email");

            //counts the number of emails ending with .biz
            if(email.contains(".biz"))
                c++;
        }

        //checks if the required condition is met
        Assert.assertTrue(c>=2);

    }


    //method to verify if the ids in the payload are unique
    @Test(priority = 5)
    public void verify_response_payload_id()
    {
        for(int i =0;i<arr.length();i++)
        {
            //stores the id at location i in the array
            int id1 = (int) arr.getJSONObject(i).get("id");

            for(int j =i+1;j<arr.length();j++)
            {
                //stores the id at location j in the array
                int id2 = (int) arr.getJSONObject(j).get("id");

                //checks if the required condition is met
                Assert.assertFalse(id1==id2);
            }

        }
    }

    //method to validate json schema
    @Test(priority = 7)
    public void validate_json_schema()throws FileNotFoundException
    {
        given().
                baseUri("https://gorest.co.in/public/v1").
                log().all().
        when().
                get("/users").
        then().
                log().ifError().
                body(matchesJsonSchemaInClasspath("jsonschema_for_get.json"));

    }

}
