package com.sokima.vmedia.modify;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PolishPathService {

    /**
     * workaround to overcome encoding / (slash)
     */
    public String polishPath(final String path) {
        if (!path.contains("/")) {
            return String.format("%s/", path);
        }

        return path;
    }
}
