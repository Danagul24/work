package org.acme.retrofit;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
@QuarkusTest
@Tag(name = "comment")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentResourceTest {

    @Test
    @Order(1)
    void getComments() {
        given()
                .when()
                .get("/comments")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(1)
    void getComment() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/comments/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(1)
    void getCommentNotFound() {
        given()
                .pathParam("id", 600)
                .when()
                .get("/comments/{id}")
                .then()
                .statusCode(400);
    }
    @Test
    @Order(2)
    void createComment() {
        JsonObject jsonObject =
                Json.createObjectBuilder()
                        .add("postId", 5)
                        .add("name", "Dana")
                        .add("email", "ddvdf")
                        .add("body", "vdsv")
                        .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/comments")
                .then()
                .statusCode(201);
    }

    @Order(4)
    @Test
    void deleteComment() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/comments/{id}")
                .then()
                .statusCode(204);
        given()
                .pathParam("id", 1)
                .when()
                .get("/comments/{id}")
                .then()
                .statusCode(200);
    }

    @Order(4)
    @Test
    void deleteCommentNotFound() {
        given()
                .pathParam("id", 2000)
                .when()
                .delete("/comments/{id}")
                .then()
                .statusCode(204);
        //.statusCode(204);
    }

    @Order(3)
    @Test
    void updateComment() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("postId", 5)
                .add("name", "Dana")
                .add("email", "vsvs")
                .add("body", "vds")
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .pathParam("id", 1)
                .when()
                .put("/comments/{id}")
                .then()
                .statusCode(200);
    }

    @Order(3)
    @Test
    void updateCommentNotFound() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("postId", 5)
                .add("name", "Dana")
                .add("email", "vsvs")
                .add("body", "vds")
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .pathParam("id", 600)
                .when()
                .put("/comments/{id}")
                .then()
                .statusCode(400);
    }
}