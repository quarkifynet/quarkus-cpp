package net.quarkify.cpp;

import io.quarkus.runtime.StartupEvent;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Path("/avatar")
public class AvatarResource {
    private Value drawMethod;

    public void onStart(@Observes StartupEvent se) {
        new Thread() {{
        final URL drawImage = getClass().getClassLoader().getResource("hello");
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Source source = null;
        try {
            source = Source.newBuilder("llvm", drawImage).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawMethod = polyglot.eval(source);
        }}.start();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public File uniqueImage() throws IOException {
        drawMethod.execute();
        return new File("avatar.png");
    }
}
