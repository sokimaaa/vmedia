package com.sokima.vmedia.modify;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnrichHrefWithProxy {

    private static final String PROXY_URL = "http://localhost:8080/proxy";

    public String enrichHref(final String html) {
        return html.replaceAll("href=\"(?!http)([^\"]*)\"", "href=\"" + PROXY_URL + "$1\"")
                .replaceAll("href='(?!http)([^']*)'", "href='" + PROXY_URL + "$1'");
    }
}
