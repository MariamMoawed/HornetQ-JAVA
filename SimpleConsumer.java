package org.example;

import org.hornetq.api.core.client.ClientConsumer;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ServerLocator;

import java.util.HashMap;
import java.util.Map;

public class SimpleConsumer {
    public static void main(String[] args) throws Exception {
        Map<String, Object> connectionParams = new HashMap<>();
        connectionParams.put("host", "localhost");
        connectionParams.put("port", 5445);

        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connectionParams);
        ServerLocator serverLocator = HornetQClient.createServerLocatorWithoutHA(transportConfiguration);
        ClientSessionFactory factory = serverLocator.createSessionFactory();
        ClientSession session = factory.createSession();
        session.start();

        ClientConsumer consumer = session.createConsumer("exampleQueue");
        ClientMessage message = consumer.receive();

        System.out.println("Received message: " + message.getBodyBuffer().readString());
        session.close();
    }
}
