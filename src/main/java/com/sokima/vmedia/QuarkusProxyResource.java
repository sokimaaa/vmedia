package com.sokima.vmedia;

import com.sokima.vmedia.template.TmQuarkusTemplate;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("proxy")
public class QuarkusProxyResource {

    @Inject
    TmQuarkusTemplate tmQuarkusTemplate;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("{path:.*}")
    public Response getProxy(@PathParam("path") Optional<String> path) {
        final String content = path.map(tmQuarkusTemplate::doTemplate)
                .orElseGet(tmQuarkusTemplate::doEmptyTemplate);

        return Response.ok(content).build();
    }
}
