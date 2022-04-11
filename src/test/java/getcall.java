import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//class to get() payload
public class getcall
{
    @Test
    public void test_get_call()
    {
        given().
                baseUri("https://gorest.co.in/public/v1").

        when().
                get("/users").
        then().
                statusCode(200);

    }
}
