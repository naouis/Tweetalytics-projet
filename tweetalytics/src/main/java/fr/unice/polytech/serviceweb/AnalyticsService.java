package fr.unice.polytech.serviceweb;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import fr.unice.polytech.Analytics;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sy306571 on 21/11/15.
 */
@Path("analytics")
public class AnalyticsService {
    public static Analytics analytics = new Analytics();

    @POST
    @Path("/trendMatch")
    public Response startAnalysis(String input) throws JSONException {
        JSONObject json = new JSONObject(input);
        analytics.startAnalytics();
        JSONArray hashtags1 = (JSONArray) json.get("tags1");
        List<String> list1 = new LinkedList<>();
        for(int i=0; i<hashtags1.length();i++){
            list1.add((String) hashtags1.get(i));
        }

        JSONArray hashtags2 = (JSONArray) json.get("tags2");
        List<String> list2 = new LinkedList<>();
        for(int i=0; i<hashtags2.length();i++){
            list2.add((String) hashtags2.get(i));
        }
        
        analytics.trendMatch(list1,list2);
        return Response.ok().build();
    }
}
