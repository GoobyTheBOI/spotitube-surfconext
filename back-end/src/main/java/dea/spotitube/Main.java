package dea.spotitube;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class Main extends Application {
    @GET
    public String helloMessage() {
        return "Hello world!";
    }
}