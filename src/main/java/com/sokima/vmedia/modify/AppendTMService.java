package com.sokima.vmedia.modify;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppendTMService {

    public String appendTm(final String content) {
        return content.replaceAll("((?<!<[^>])\\b[a-zA-Z]{6}\\b(?![^<]*>))", "$1™");
    }
}
