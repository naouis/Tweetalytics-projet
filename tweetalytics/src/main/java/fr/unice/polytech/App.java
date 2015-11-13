package fr.unice.polytech;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        ArrayList<String> terms = Lists.newArrayList("twitter", "api");
        hosebirdEndpoint.trackTerms(terms);

        Authentication hosebirdAuth = new OAuth1("zUS8fZ2MRdLLi8MO1WyH2fUlh", "59JyxFXFvxQdcmL6Pl8FcqVlQUYTfk8K0WMNSppAUfoDJVDaCj", "2319757231-2mFq8SkzxEa9FTVzedPQ3yzGoQzRIllwoVv3Yku", "i3yCNQQaKldLiLQcDqf8zjCXLIGwTQB5Onlen3wDVJGRJ");

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);

        Client hosebirdClient = builder.build();

        hosebirdClient.connect();

        while (!hosebirdClient.isDone()) {
            String msg = msgQueue.take();
            System.out.println(msg);
        }

        hosebirdClient.stop();
    }
}
