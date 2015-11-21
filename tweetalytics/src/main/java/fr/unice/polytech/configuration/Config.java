/*package fr.unice.polytech.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import fr.unice.polytech.TwitterStreamConsumer;
import fr.unice.polytech.TwitterStreamSaver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * Application parameter.
 * It has been declared as a listener in web.xml
 */
/*@WebListener
public class Config implements ServletContextListener {

    final String tableName = "products";
    AmazonDynamoDBClient dbClient;
    DynamoDB db;
    Region region;

    /*
     Initialise AWS stuff when the servlet is initialised and makes it available through the
     {{@value "config"}} servlet context attribute
     */
  /*  public void contextInitialized(ServletContextEvent event){

        TwitterStreamConsumer streamConsumer = new TwitterStreamConsumer();
        streamConsumer.ConsumerStart();

        TwitterStreamSaver streamSaver = new TwitterStreamSaver();
        streamSaver.SaverStart();

        String consumedData = streamConsumer.Consume();
        while(consumedData != null){
            try {
                streamSaver.Save(consumedData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            consumedData = streamConsumer.Consume();
        }

        streamConsumer.ConsumerStop();

        // event.getServletContext().setAttribute("config", this);
        // System.err.println("Config registered");
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}*/