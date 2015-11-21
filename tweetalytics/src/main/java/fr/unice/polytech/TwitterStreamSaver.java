package fr.unice.polytech;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwitterStreamSaver {

    public AmazonDynamoDBClient clientDB;
    public DynamoDB myDb;
    public Table myTable;

    public void SaverStart() {
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

    public void Save(String consumedData) throws IOException {
//        System.out.println("New Line: " + consumedData);

        ObjectMapper mapper = new ObjectMapper();
        Tweet tweetDeconstructed = mapper.readValue(consumedData, Tweet.class);

        // Todo: Find a better way to check!
        if(!consumedData.contains("limit")) {
//            System.out.println("Deconstructed : " + tweetDeconstructed.getTimestampMs() + " "
//                                                    + tweetDeconstructed.getUser().getName());
            List<String> hashtags = new ArrayList<String>();
            for(int i = 0; i < tweetDeconstructed.getEntities().getHashtags().size(); i++){
                hashtags.add(tweetDeconstructed.getEntities().getHashtags().get(i).getText());
            }

            Item myTweet = new Item()
                    .withPrimaryKey("timestamp", Long.parseLong(tweetDeconstructed.getTimestampMs()))
                    .withString("username", tweetDeconstructed.getUser().getName())
                    .withList("hashtags", hashtags)
                    .withString("localisation", "Geo")
                    .withNumber("retweets",tweetDeconstructed.getRetweetCount());

            PutItemOutcome outcome = myTable.putItem(myTweet);
        }
    }

}
