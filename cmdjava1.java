import org.asteriskjava.live.AsteriskServer;

import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.action.ParkAction;
import org.asteriskjava.live.Extension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.asteriskjava.live.ManagerCommunicationException;

public class cmdjava1{
	private AsteriskServer asteriskServer;

	public cmdjava1()
	{
		asteriskServer = new DefaultAsteriskServer("localhost", "admin", "asterisk@321");

	}

	public void run() throws IOException{
	Runtime rt = Runtime.getRuntime();
	String[] commands = {"system.exe","dahdi show status"};
	Process proc = rt.exec(commands);

	BufferedReader stdInput = new BufferedReader(new 
	     InputStreamReader(proc.getInputStream()));

	BufferedReader stdError = new BufferedReader(new 
	     InputStreamReader(proc.getErrorStream()));

	// read the output from the command
	System.out.println("Here is the standard output of the command:\n");
	String s = null;
	while ((s = stdInput.readLine()) != null) {
	    System.out.println(s);
	}

	// read any errors from the attempted command
	System.out.println("Here is the standard error of the command (if any):\n");
	while ((s = stdError.readLine()) != null) {
	    System.out.println(s);
	}
}

public static void main(String[] args) throws Exception
	{
		cmdjava1 helloLive = new cmdjava1();
		helloLive.run();
		//helloLive.sample();

	}

}
