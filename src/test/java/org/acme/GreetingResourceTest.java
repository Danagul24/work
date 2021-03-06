package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.resource.GreetingResource;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Tag(name = "post")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(GreetingResource.class)
class GreetingResourceTest {
    @Test
    @Order(2)
    void getPosts() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void getPost() {
        given()
                .pathParam("id", 1)
                .when()
                .get( "/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(1)
    void getPostNotFound() {
        given()
                .pathParam("id", 600)
                .when()
                .get("/{id}")
                .then()
                .statusCode(400);
    }
    @Test
    @Order(1)
    void createPost() {
        JsonObject jsonObject =
                Json.createObjectBuilder()
                        .add("name", "Dana")
                        .add("title", "ddvdf")
                        .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Order(4)
    @Test
    void deletePost() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }

    @Order(4)
    @Test
    void deletePostNotFound() {
        given()
                .pathParam("id", 2000)
                .when()
                .delete("{id}")
                .then()
                .statusCode(400);
    }

    @Order(3)
    @Test
    void updatePost() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "Dana")
                .add("title", "vsvs")
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .pathParam("id", 1)
                .when()
                .put("{id}")
                .then()
                .statusCode(200);
    }

    @Order(3)
    @Test
    void updatePostNotFound() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "Dana")
                .add("title", "vsvs")
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .pathParam("id", 600)
                .when()
                .put("{id}")
                .then()
                .statusCode(400);
    }
}