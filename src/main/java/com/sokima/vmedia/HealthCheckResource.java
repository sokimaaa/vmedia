package com.sokima.vmedia;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("ping")
public class HealthCheckResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }
}
