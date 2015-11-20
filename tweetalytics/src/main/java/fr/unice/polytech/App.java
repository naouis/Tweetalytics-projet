package fr.unice.polytech;

import java.io.IOException;

public class App
{
    public static void main(String[] args) throws InterruptedException, IOException {

        TwitterStreamConsumer streamConsumer = new TwitterStreamConsumer();
        streamConsumer.ConsumerStart();

        TwitterStreamSaver streamSaver = new TwitterStreamSaver();
        streamSaver.SaverStart();

        String consumedData = streamConsumer.Consume();
        while(consumedData != null){
            streamSaver.Save(consumedData);
            consumedData = streamConsumer.Consume();
        }

        streamConsumer.ConsumerStop();
    }
}