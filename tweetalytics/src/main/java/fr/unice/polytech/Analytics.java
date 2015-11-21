package fr.unice.polytech;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import fr.unice.polytech.configuration.Config;

import java.util.*;

public class Analytics {

    private AmazonDynamoDBClient clientDB;
    private DynamoDB myDb;
    private Table myTable;

    public Analytics(){}

    public void startAnalytics(){
        clientDB = new AmazonDynamoDBClient(
                new AWSCredentialsProviderChain(
                        new InstanceProfileCredentialsProvider(),
                        new ProfileCredentialsProvider()
                )
        );
        clientDB.setRegion(Region.getRegion(Regions.US_WEST_2));

        myDb = new DynamoDB(clientDB);

        myTable = myDb.getTable(Config.tableName);
    }

    private List<Integer> hashtagEvolution(List<String> hashtags, String intervalInMinutes, String startingSinceInMinutes){
        Long beginingTime = (new Date()).getTime() - (Long.parseLong(startingSinceInMinutes)*60L*1000L);
        Long endingTime = (new Date()).getTime();
        Long interval = (Long.parseLong(intervalInMinutes)*60L*1000L);

        List<Integer> countHashtag = new ArrayList<>();
        Integer j = 0;

        for(Long i = beginingTime; i < endingTime; i+= interval) {

            Map<String, Object> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":v_timestampBeg", i);
            expressionAttributeValues.put(":v_timestampEnd", i+interval);
            expressionAttributeValues.put(":v_hashtags", hashtags);

            ItemCollection<ScanOutcome> items = myTable.scan(
                    "myTimestamp > :v_timestampBeg and myTimestamp < :v_timestampEnd and hashtags = :v_hashtags",
                    "myTimestamp, hashtags",
                    null,
                    expressionAttributeValues
            );

            // Todo: Don't why can't remove it, the count fails.
            for(Item item: items){}

            if(j == 0) countHashtag.add(items.getTotalCount());
            else countHashtag.add(items.getTotalCount() + countHashtag.get(j-1));
            j++;
        }

        return countHashtag;
    }

    public void trendMatch(List<String> hashtags1, List<String> hashtags2, String intervalInMinutes, String startingSinceInMinutes){

        List<Integer> countHashtags1 = hashtagEvolution(hashtags1, intervalInMinutes, startingSinceInMinutes);
        List<Integer> countHashtags2 = hashtagEvolution(hashtags2, intervalInMinutes, startingSinceInMinutes);

        System.err.println("Trend Match:");
        System.err.println("================");
        for(int i = 0; i < hashtags1.size(); i++){
            System.out.print(hashtags1.get(i) + " ");
        }
        System.err.println();
        for(int i = 0; i < hashtags2.size(); i++){
            System.out.print(hashtags2.get(i) + " ");
        }
        System.err.println();
        System.err.println("----------------");
        for(int i = 0; i < countHashtags1.size(); i++) {
            System.err.println("Interval(" + (i+1) + "):");
            System.err.println(countHashtags1.get(i));
            System.err.println(countHashtags2.get(i));
        }
        System.err.println("================");
    }

    public void localityMatch(String hashtag){

    }

    private List<String> getNMostPopularHashtags(int N, String intervalInMinutes, String startingSinceInMinutes){
        Long beginingTime = (new Date()).getTime() - (Long.parseLong(startingSinceInMinutes)*60L*1000L);
        Long endingTime = (new Date()).getTime();

        List<String> mostPopularHashtags = new ArrayList<>();

        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":v_timestampBeg", beginingTime);
        expressionAttributeValues.put(":v_timestampEnd", endingTime);

        ItemCollection<ScanOutcome> items = myTable.scan(
                "myTimestamp > :v_timestampBeg and myTimestamp < :v_timestampEnd",
                "hashtags",
                null,
                expressionAttributeValues
        );

        for(int i = 0; i < N; i++);

        return mostPopularHashtags;
    }

    public void trendEvolution(Integer k, String intervalInMinutes, String startingSinceInMinutes){
        List<String> mostPopularHashtags = getNMostPopularHashtags(k, intervalInMinutes, startingSinceInMinutes);
        List<List<Integer>> mostPopularHashtagsEvolution = new ArrayList<>();
        List<String> tempHashtags = new ArrayList<>();

        System.err.println("Trend Evolution:");
        System.err.println("================");

        for(String hashtag: mostPopularHashtags){
            tempHashtags.add(hashtag);
            mostPopularHashtags.add(hashtag, hashtagEvolution(tempHashtags, intervalInMinutes, startingSinceInMinutes));
        }

        System.out.println("================");
    }

    public void watchdog(String hashtag){

    }

    public void tweetPropagation(){}

    public void retweetBot(){}

    public void stopAnalytics(){

    }

}
