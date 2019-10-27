package post;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.*;

public class TestDataClass {
    static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = "https://reqres.in/";
    }

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType(ContentType.JSON) // same as .header("Content-Type", "application/json")
            .setBasePath("/api/users")
            .build();

    RequestSpecification requestSpecRegister = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType(ContentType.JSON) // same as .header("Content-Type", "application/json")
            .setBasePath("/api/register")
            .build();

    ResponseSpecification responseSpecRegister = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("id"))
            .build();

    ResponseSpecification responseSpecLoginUser = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("token"))
            .build();

    ResponseSpecification responseSpecLoginFailedUser = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("error"))
            .build();
}

