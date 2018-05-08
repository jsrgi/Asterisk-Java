
import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AsyncAgiServer;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;

import java.util.Date;

public class GetDataScript extends BaseAgiScript
{
    public void service(AgiRequest request, AgiChannel channel) throws AgiException
    {
        channel.streamFile("demo-congrats");
        channel.sayDateTime(new Date().getTime());
    }

    public static void main(String[] args) throws Exception
    {
        ManagerConnection connection;
        AsyncAgiServer agiServer;

        connection = new DefaultManagerConnection("localhost", "admin", "asterisk@321");
        agiServer = new AsyncAgiServer(new GetDataScript());
        connection.addEventListener(agiServer);
        connection.login();

        while (true)
        {
            Thread.sleep(1000L);
        }
    }

	
}

