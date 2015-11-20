package fr.unice.polytech;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

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

        System.out.println("Saver started!");
    }

    public void Save(String consumedData){
        System.out.println("New Line: " + consumedData);

        Item tweet = new Item()
                .withPrimaryKey("tweetTime", System.currentTimeMillis())
                .withString("tweetUser", consumedData);

        myTable.putItem(tweet);
    }

}
