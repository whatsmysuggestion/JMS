import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
 
    //private static String url =  ActiveMQConnection.DEFAULT_BROKER_URL;
 
    private static String subject = "TESTQUEUE";

    public static void main(String[] args) throws JMSException {
  
     ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
     
        Connection connection = connectionFactory.createConnection();
        connection.start(); 
      
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
       
        
        TextMessage message = session.createTextMessage("HELLO");
       // TextMessage message = session.createTextMessage(new FileMessage().getFileAsqString(new File("d:/a.txt")));
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");
        connection.close();
    }
}