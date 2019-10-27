package get;

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

public class GetTestSingleUserToObj {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setContentType(ContentType.JSON) // same as .header("Content-Type", "application/json")
            .setBasePath("/api/users/{id}")
            .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$", hasKey("data"))
            .build();

    @Test
    public void restGetResponseInObject() {
        //User user = new User();
        User user = given()
                .spec(requestSpec)
                .pathParam("id", 3)
                .when()
                .get()
                .then()
                .log().body()
                .extract()
                .body()
                .as(User.class);

        //Assert.assertEquals(user.getId(),3);
        Assert.assertEquals(user.getEmail(),"emma.wong@reqres.in");

    }
}
