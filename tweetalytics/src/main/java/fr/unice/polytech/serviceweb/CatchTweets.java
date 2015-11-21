package fr.unice.polytech.serviceweb;

import fr.unice.polytech.TwitterStreamConsumer;
import fr.unice.polytech.TwitterStreamSaver;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by sy306571 on 21/11/15.
 */
@Path("catch")
public class CatchTweets {
    private static TwitterStreamConsumer streamConsumer = new TwitterStreamConsumer();
    private static TwitterStreamSaver streamSaver = new TwitterStreamSaver();

    @POST
    @Path("/start")
    public Response startStream() throws IOException {
        streamConsumer.ConsumerStart();
        streamSaver.SaverStart();

        String consumedData = streamConsumer.Consume();
        while(consumedData != null && !streamConsumer.isDone()){
            streamSaver.Save(consumedData);
            consumedData = streamConsumer.Consume();
        }
        return Response.ok().build();
    }

    @POST
    @Path("/stop")
    public Response stopStream(){
        streamConsumer.ConsumerStop();
        return Response.ok().build();
    }
}
