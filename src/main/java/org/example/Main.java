package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            // Run the producer
            SimpleProducer.main(null);

            // Run the consumer
            SimpleConsumer.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}