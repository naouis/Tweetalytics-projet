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

public class TwitterStreamConsumer {

    public BlockingQueue<String> msgQueue;
    public Client hosebirdClient;

    public void ConsumerStart(){
        msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        ArrayList<String> terms = Lists.newArrayList("twitter");
        hosebirdEndpoint.trackTerms(terms);

        Authentication hosebirdAuth = new OAuth1("zUS8fZ2MRdLLi8MO1WyH2fUlh", "59JyxFXFvxQdcmL6Pl8FcqVlQUYTfk8K0WMNSppAUfoDJVDaCj", "2319757231-2mFq8SkzxEa9FTVzedPQ3yzGoQzRIllwoVv3Yku", "i3yCNQQaKldLiLQcDqf8zjCXLIGwTQB5Onlen3wDVJGRJ");

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);

        hosebirdClient = builder.build();

        hosebirdClient.connect();
    }

    public String Consume(){
        String msg;
        try {
            msg = msgQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return msg;
    }

    public void ConsumerStop(){
        hosebirdClient.stop();
    }

    public boolean isDone(){
        return hosebirdClient.isDone();
    }

}
