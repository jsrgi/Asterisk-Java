import java.io.IOException;
import java.net.InetAddress;

import org.asteriskjava.fastagi.AgiServerThread;
import org.asteriskjava.fastagi.DefaultAgiServer;
import org.asteriskjava.live.DefaultAsteriskServer;

public class HangupTest {
	public static void main(String[] args) throws IOException {
		  AgiServerThread agiServerThread=new AgiServerThread();
		  agiServerThread.setAgiServer(new DefaultAgiServer());
		  agiServerThread.setDaemon(false);
		  agiServerThread.startup();
		  DefaultAsteriskServer server=new DefaultAsteriskServer("localhost",1234,"manager","obelisk");
		  server.initialize();
		  server.originateToApplication("SIP/phone-02","AGI","agi://" + InetAddress.getLocalHost().getHostAddress() + "/"+ HangupTest.class.getName()+ ", arg1,,arg3",30000);
		}
		 

}
