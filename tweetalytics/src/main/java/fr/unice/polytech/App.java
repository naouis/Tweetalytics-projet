package fr.unice.polytech;

public class App 
{
    public static void main(String[] args) throws InterruptedException {

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
