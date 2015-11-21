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
    private Long interval = (20L*60L*1000L);

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

    public void trendMatch(List<String> hashtags1, List<String> hashtags2){
        Long beginingTime = (new Date()).getTime() - (5L*60L*60L*1000L);
        Long endingTime = (new Date()).getTime();

        List<Integer> countHashtag1 = new ArrayList<Integer>();
        List<Integer> countHashtag2 = new ArrayList<Integer>();
        Integer j = 0;

        for(Long i = beginingTime; i < endingTime; i+= interval) {

            Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
            expressionAttributeValues.put(":v_timestampBeg", i);
            expressionAttributeValues.put(":v_timestampEnd", i+interval);
            expressionAttributeValues.put(":v_hashtags", hashtags1);

            ItemCollection<ScanOutcome> items1 = myTable.scan(
                    "myTimestamp > :v_timestampBeg and myTimestamp < :v_timestampEnd and hashtags = :v_hashtags",
                    "myTimestamp, hashtags",
                    null,
                    expressionAttributeValues
            );

            for (Item item: items1) {
                System.err.println(item.toJSONPretty());
            }

            if(j == 0) countHashtag1.add(items1.getTotalCount());
            else countHashtag1.add(items1.getTotalCount() + countHashtag1.get(j-1));


            Map<String, Object> expressionAttributeValues2 = new HashMap<String, Object>();
            expressionAttributeValues2.put(":v_timestampBeg", i);
            expressionAttributeValues2.put(":v_timestampEnd", i+interval);
            expressionAttributeValues2.put(":v_hashtags2", hashtags2);

            ItemCollection<ScanOutcome> items2 = myTable.scan(
                    "myTimestamp > :v_timestampBeg and myTimestamp < :v_timestampEnd and hashtags = :v_hashtags2",
                    "myTimestamp, hashtags",
                    null,
                    expressionAttributeValues2
            );

            for (Item item: items2) {
                System.err.println(item.toJSONPretty());
            }

            if(j == 0) countHashtag2.add(items2.getTotalCount());
            else countHashtag2.add(items2.getTotalCount() + countHashtag2.get(j-1));


            System.err.println("Begin at: " + i + " , End at: " + (i+interval));
            System.err.println(countHashtag1.get(j));
            System.err.println(countHashtag2.get(j));
            j++;
        }
    }

    public void localityMatch(String hashtag){

    }

    public void trendEvolution(Integer k){

    }

    public void watchdog(String hashtag){

    }

    public void tweetPropagation(){}

    public void retweetBot(){}

    public void stopAnalytics(){

    }

}
