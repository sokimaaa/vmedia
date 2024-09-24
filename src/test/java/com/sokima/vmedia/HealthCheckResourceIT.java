package com.sokima.vmedia;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HealthCheckResourceIT {

    @Test
    void ping_stateless_getPongResponse() {
        given()
                .when().post("/ping")
                .then()
                .statusCode(200)
                .body(is("pong"));
    }
}