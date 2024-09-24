package com.sokima.vmedia;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class QuarkusProxyResourceIT {

    private static final Logger log = LoggerFactory.getLogger(QuarkusProxyResourceIT.class);

    @Test
    void getProxy_withoutPath_appendedWithTmAndHrefUpdated() {
        final var response = given()
                .accept(MediaType.TEXT_HTML)
                .when()
                .get("/proxy")
                .then()
                .statusCode(200)
                .extract()
                .response();

        final var actual = response.getBody().asString();

        log.info(actual);

        // counting number of TM chars on page
        final char targetChar = '™';
        int count = 0;

        for (int i = 0; i < actual.length(); i++) {
            if (actual.charAt(i) == targetChar) {
                count++;
            }
        }

        assertThat(count).isEqualTo(50);

        // counting href updated with proxy
        final String urlToCheck = "http://localhost:8080/proxy/";
        int countUrl = 0;

        int index = 0;
        while ((index = actual.indexOf(urlToCheck, index)) != -1) {
            countUrl++;
            index += urlToCheck.length(); // Move past the last found occurrence
        }

        assertThat(countUrl).isEqualTo(66);
    }

    @Test
    void getProxy_withoutPath_modifiedQuarkusIoPage() {
        /**
         * quote at the end of quarkus.io from Lead Developer on the Adoptium Technical Steering Committee
         */
        final var expectedParsedWordsFromQuarkusIo = List.of("The", "live",
                "coding", "fast", "feedback", "loop", "was", "very", "useful", "when",
                "developing", "with", "Quarkus", "Although", "fast", "startup", "was",
                "nice", "it", "wasn’t", "critical", "since", "our", "apps", "are", "long-running",
                "but", "during", "development", "as", "we", "tweaked", "our", "APIs", "being",
                "able", "to", "immediately", "re-test", "saved", "us", "a", "ton", "of", "development",
                "time");

        final var alteredWords = List.of("coding™", "useful™", "during™");


        final var response = given()
                .accept(MediaType.TEXT_HTML)
                .when()
                .get("/proxy")
                .then()
                .statusCode(200)
                .extract()
                .response();

        final var actual = response.getBody().asString();

        for (final String expectedWord : expectedParsedWordsFromQuarkusIo) {
            assertThat(actual).contains(expectedWord);
        }

        for (final String expectedWord : alteredWords) {
            assertThat(actual).contains(expectedWord);
        }
    }

    @Test
    void getProxy_withPath_modifiedQuarkusIoGuidesPage() {
        final var expectedParsedWordsFromQuarkusIoGuides = List.of(
                "Quarkus", "Security", "with", "Jakarta", "Persistence",
                "You", "can", "configure", "your", "application", "to", "use", "Jakarta",
                "Persistence", "to", "store", "users", "identities"
        );

        final var response = given()
                .accept(MediaType.TEXT_HTML)
                .when()
                .get("/proxy/guides")
                .then()
                .statusCode(200)
                .extract()
                .response();

        final var actual = response.getBody().asString();

        for (final String expectedWord : expectedParsedWordsFromQuarkusIoGuides) {
            assertThat(actual).contains(expectedWord);
        }
    }

    @Test
    void getProxy_withDeepPath_modifiedQuarkusIoGuidesTutorialPage() {
        final var expectedParsedWordsFromQuarkusGuidesTutorial = List.of(
                "Create", "new", "tutorial", "that", "guides",
                "users", "through", "creating", "running", "and", "testing", "a", "Quarkus",
                "application", "that", "uses", "annotations", "from", "an", "imaginary", "extension"
        );


        final var response = given()
                .accept(MediaType.TEXT_HTML)
                .when()
                .get("/proxy/guides/doc-create-tutorial")
                .then()
                .statusCode(200)
                .extract()
                .response();

        final var actual = response.getBody().asString();

        for (final String expectedWord : expectedParsedWordsFromQuarkusGuidesTutorial) {
            assertThat(actual).contains(expectedWord);
        }
    }
}