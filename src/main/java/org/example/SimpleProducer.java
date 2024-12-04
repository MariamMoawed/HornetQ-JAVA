package org.example;

import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ServerLocator;

import java.util.HashMap;
import java.util.Map;

public class SimpleProducer {
    public static void main(String[] args) throws Exception {
        Map<String, Object> connectionParams = new HashMap<>();
        connectionParams.put("host", "localhost");
        connectionParams.put("port", 5445);

        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connectionParams);
        ServerLocator serverLocator = HornetQClient.createServerLocatorWithoutHA(transportConfiguration);
        ClientSession session;
        try (ClientSessionFactory factory = serverLocator.createSessionFactory()) {
            session = factory.createSession();
        }
        ClientProducer producer = session.createProducer("exampleQueue");

        ClientMessage message = session.createMessage(true);
        message.getBodyBuffer().writeString("Hello, HornetQ!");

        producer.send(message);
        session.close();
    }
}
