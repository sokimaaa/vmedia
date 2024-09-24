package com.sokima.vmedia.modify;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class EnrichHrefWithProxyTest {

    @Inject
    EnrichHrefWithProxy enrichHrefWithProxy;

    @Test
    void enrichHref_hrefBlock_replacedWithProxy() {
        final var actual = enrichHrefWithProxy.enrichHref("""
                <a href="/guides">Read more guides</a>
                """);

        Assertions.assertEquals("""
                <a href="http://localhost:8080/proxy/guides">Read more guides</a>
                """, actual);
    }

    @Test
    void enrichHref_hrefBlockSingleQuotes_replacedWithProxy() {
        final var actual = enrichHrefWithProxy.enrichHref("""
                <a href='/guides'>Read more guides</a>
                """);

        Assertions.assertEquals("""
                <a href='http://localhost:8080/proxy/guides'>Read more guides</a>
                """, actual);
    }
}