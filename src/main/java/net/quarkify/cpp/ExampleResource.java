package net.quarkify.cpp;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public File hello() throws IOException {
        final URL drawImage = getClass().getClassLoader().getResource("hello");
        Context polyglot = Context.newBuilder().
                allowAllAccess(true).build();
        Source source = Source.newBuilder("llvm", drawImage).build();
        Value cpart = polyglot.eval(source);
        cpart.execute();
        return new File("fruit.png");
    }
}
