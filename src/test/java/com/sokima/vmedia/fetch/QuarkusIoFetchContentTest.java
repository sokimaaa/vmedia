package com.sokima.vmedia.fetch;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class QuarkusIoFetchContentTest {

    @Inject
    @RestClient
    QuarkusIoFetchContent quarkusIoFetchContent;

    @Test
    void getContent_withoutPathParam_quarkusIoPage() {
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

        final var actual = quarkusIoFetchContent.getContent();

        for (final String expectedWord : expectedParsedWordsFromQuarkusIo) {
            assertThat(actual).contains(expectedWord);
        }
    }

    @Test
    void getContent_withPathParam_quarkusIoGuidesPage() {
        final var expectedParsedWordsFromQuarkusGuides = List.of(
                "Quarkus", "Security", "with", "Jakarta", "Persistence",
                "You", "can", "configure", "your", "application", "to", "use", "Jakarta",
                "Persistence", "to", "store", "users", "identities"
        );

        final var actual = quarkusIoFetchContent.getContent("guides/");


        for (final String expectedWord : expectedParsedWordsFromQuarkusGuides) {
            assertThat(actual).contains(expectedWord);
        }
    }

    @Test
    void getContent_withDeepPathParam_quarkusIoGuidesTutorialPage() {
        final var expectedParsedWordsFromQuarkusGuidesTutorial = List.of(
                "Create", "new", "tutorial", "that", "guides",
                "users", "through", "creating", "running", "and", "testing", "a", "Quarkus",
                "application", "that", "uses", "annotations", "from", "an", "imaginary", "extension"
        );

        final var actual = quarkusIoFetchContent.getContent("guides/doc-create-tutorial");

        for (final String expectedWord : expectedParsedWordsFromQuarkusGuidesTutorial) {
            assertThat(actual).contains(expectedWord);
        }
    }
}