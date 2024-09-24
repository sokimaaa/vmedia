package com.sokima.vmedia.modify;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AppendTMServiceTest {

    @Inject
    AppendTMService appendTMService;

    @Test
    void appendTm_sixSymbWord_appendTM() {
        final var actual = appendTMService.appendTm("readme");
        Assertions.assertEquals("readme™", actual);
    }

    @Test
    void appendTm_notSixSymbWord_ignore() {
        final var actual = appendTMService.appendTm("quarkus");
        Assertions.assertEquals("quarkus", actual);
    }

    @Test
    void appendTm_html_shouldIgnoreHtmlElements() {
        final var actual = appendTMService.appendTm("""
                <html>
                  <head>
                    <title>Sample Page</title>
                  </head>
                  <body>
                    <div classs="content">Welcom to Quarkus platform.</div>
                    <p>This is a simple exampe to demonstrate the modification.</pppppp>
                    <a href="/guides">Read more guides</a>
                  </body>
                </html>
                """);
        Assertions.assertEquals("""
                <html>
                  <head>
                    <title>Sample™ Page</title>
                  </head>
                  <body>
                    <div classs="content">Welcom™ to Quarkus platform.</div>
                    <p>This is a simple™ exampe™ to demonstrate the modification.</pppppp>
                    <a href="/guides">Read more guides™</a>
                  </body>
                </html>
                """, actual);
    }
}