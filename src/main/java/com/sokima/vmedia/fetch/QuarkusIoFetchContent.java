package com.sokima.vmedia.fetch;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface QuarkusIoFetchContent {

    @GET
    @Produces(MediaType.TEXT_HTML)
    String getContent();

    @GET
    @Path("{path}")
    @Produces(MediaType.TEXT_HTML)
    String getContent(@PathParam("path") String path);
}
