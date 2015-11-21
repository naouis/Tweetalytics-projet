package fr.unice.polytech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main(String[] args) throws InterruptedException, IOException {

//        TwitterStreamConsumer streamConsumer = new TwitterStreamConsumer();
//        streamConsumer.ConsumerStart();
//
//        TwitterStreamSaver streamSaver = new TwitterStreamSaver();
//        streamSaver.SaverStart();
//
//        String consumedData = streamConsumer.Consume();
//        while(consumedData != null){
//            streamSaver.Save(consumedData);
//            consumedData = streamConsumer.Consume();
//        }
//
//        streamConsumer.ConsumerStop();

        Analytics analyticsProcessor = new Analytics();
        analyticsProcessor.startAnalytics();

        // Analytics
        // Trend Match Test
        List<String> hashtags1 = new ArrayList<>();
        List<String> hashtags2 = new ArrayList<>();
        hashtags1.add("PrayForParis");
        hashtags2.add("MTVStars");
        analyticsProcessor.trendMatch(hashtags1, hashtags2, "20", "300");

        //Trend Evolution Test
        analyticsProcessor.trendEvolution(7, "20", "300");



        analyticsProcessor.stopAnalytics();

    }
}