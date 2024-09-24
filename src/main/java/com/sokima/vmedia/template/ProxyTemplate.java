package com.sokima.vmedia.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProxyTemplate {

    private static final Logger log = LoggerFactory.getLogger(ProxyTemplate.class);

    public String doTemplate(final String path) {
        final String polishedPath = polishPath(path);
        final String fetchedHtml = fetchContent(polishedPath);
        final String modifiedContent = modifyContent(fetchedHtml);
        final String enrichedContent = enrichWithProxy(modifiedContent);
        log.debug("Processed content: {}", enrichedContent);

        return enrichedContent;
    }

    public String doEmptyTemplate() {
        final String fetchedHtml = fetchContent();
        final String modifiedContent = modifyContent(fetchedHtml);
        final String enrichedContent = enrichWithProxy(modifiedContent);
        log.debug("Processed content: {}", enrichedContent);

        return enrichedContent;
    }

    protected abstract String polishPath(String path);

    protected abstract String fetchContent(String path);

    protected abstract String fetchContent();

    protected abstract String modifyContent(String content);

    protected abstract String enrichWithProxy(String html);
}
