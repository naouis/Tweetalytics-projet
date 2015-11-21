package fr.unice.polytech;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.List;

public class Analytics {

    private AmazonDynamoDBClient clientDB;
    private DynamoDB myDb;
    private Table myTable;

    public void startAnalytics(){
        clientDB = new AmazonDynamoDBClient(
                new AWSCredentialsProviderChain(
                        new InstanceProfileCredentialsProvider(),
                        new ProfileCredentialsProvider()
                )
        );
        clientDB.setRegion(Region.getRegion(Regions.US_WEST_2));

        myDb = new DynamoDB(clientDB);

        myTable = myDb.getTable("tweetsTable");
    }

    public void trendMatch(List<String> hashtags1, List<String> hashtags2){

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
