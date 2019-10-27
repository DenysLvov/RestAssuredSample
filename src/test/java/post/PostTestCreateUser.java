package post;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class PostTestCreateUser extends TestDataClass{

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("id"))
            .build();

    @Test
    public void restPostCreateUser() {
        given()
                .spec(requestSpec)
                .body("{\"name\": \"TestUser\", \"job\": \"Automation\"}")
                .post()
                .then()
                .spec(responseSpec)
                .and()
                .log().body()
                .body("name", equalTo("TestUser"))
                .and()
                .body("job", equalTo("Automation"));
    }

    @Test
    public void restPostRegisterUser() {
        given()
                .spec(requestSpecRegister)
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                .post()
                .then()
                .spec(responseSpecRegister)
                .body("id", equalTo(4))
                .log().body();
    }

    @Test
    public void restPostUnRegisterUser() {
       int s = (given()
                .spec(requestSpecRegister)
                .body("{\"email\": \"unregistered@com.com\", \"password\": \"pistol\"}")
                .post()
                .then()
                //.spec(responseSpecRegister);
                .extract()
                .statusCode());
        Assert.assertEquals(400, s);
    }

    @Test
    public void restPostLoginUser() {
            given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .basePath("/api/login")
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .post()
                .then()
                .spec(responseSpecLoginUser)
                .log().body();

    }

    @Test
    public void restPostFailedLoginUser() {
        given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .basePath("/api/login")
                .body("{\"email\": \"peter@klaven\"}")
                .post()
                .then()
                .spec(responseSpecLoginFailedUser)
                .log().body()
                .body("error", equalTo("Missing password"));

    }
}

