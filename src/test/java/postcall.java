import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

//class to get() payload
public class postcall
{
    @BeforeClass
    public void beforeclass()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://gorest.co.in/public/v1").
                addHeader("Content-type","application/json").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_pos_request()throws IOException,FileNotFoundException
    {
        String token = "489f98c45846d05b49febcd91d7015e4387a916da109e14d00cdbd1ba053b887";
        excelcall e = new excelcall();
        JSONObject obj = e.excel();
        String payload = obj.toString();

        given().
                header("Authorization","Bearer " +token).
                body(payload).
        when().
                post("/users").
        then();


    }

}
