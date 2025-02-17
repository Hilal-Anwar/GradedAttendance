package org.graded_classes.graded_attendance;

import java.io.InputStream;
import java.net.URL;

public class GradedResourceLoader {
    public static URL loadURL(String path) {
        return GradedResourceLoader.class.getResource(path);
    }

    public static String load(String path) {
        return loadURL(path).toExternalForm();
    }

    public static InputStream loadStream(String name) {
        return GradedResourceLoader.class.getResourceAsStream(name);
    }
}
