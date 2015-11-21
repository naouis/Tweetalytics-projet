package fr.unice.polytech.serviceweb;

import com.amazonaws.util.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by sy306571 on 21/11/15.
 */
@Path("analytics")
public class AnalyticsService {
    @GET
    @Path("/trendEvolution")
    @Produces("application/json")
    public Response trendEvolution(){
        //Todo: execute analysis
        JSONObject answer = new JSONObject();
        //Todo: put result into json object
        return Response.ok().entity(answer.toString()).build();
    }
}
