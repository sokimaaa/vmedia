package com.sokima.vmedia.template;

import com.sokima.vmedia.fetch.QuarkusIoFetchContent;
import com.sokima.vmedia.modify.AppendTMService;
import com.sokima.vmedia.modify.EnrichHrefWithProxy;
import com.sokima.vmedia.modify.PolishPathService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class TmQuarkusTemplate extends ProxyTemplate {

    @Inject
    PolishPathService polishPathService;

    @Inject
    AppendTMService appendTMService;

    @Inject
    EnrichHrefWithProxy enrichHrefWithProxy;

    @Inject
    @RestClient
    QuarkusIoFetchContent quarkusIoFetchContent;

    @Override
    protected String polishPath(final String path) {
        return polishPathService.polishPath(path);
    }

    @Override
    protected String fetchContent(final String path) {
        return quarkusIoFetchContent.getContent(path);
    }

    @Override
    protected String fetchContent() {
        return quarkusIoFetchContent.getContent();
    }

    @Override
    protected String modifyContent(final String content) {
        return appendTMService.appendTm(content);
    }

    @Override
    protected String enrichWithProxy(final String html) {
        return enrichHrefWithProxy.enrichHref(html);
    }
}
