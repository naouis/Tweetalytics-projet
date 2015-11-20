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
            // Todo: Solve the consumption problem, it just consumes 1 tweet and blocks in the second!
            consumedData = streamConsumer.Consume();
        }

        streamConsumer.ConsumerStop();
    }
}