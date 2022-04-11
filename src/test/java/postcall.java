import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//class to get() payload
public class postcall
{
    @Test(priority = 1)
    public void test_statuscode()
    {
        given().
                baseUri("https://gorest.co.in/public/v1").

                when().
                post("/users").
                then().
                statusCode(200);

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
