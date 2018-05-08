
import org.asteriskjava.live.AsteriskServer;



import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.action.ParkAction;
import org.asteriskjava.live.Extension;
import java.util.Timer;
import java.util.TimerTask;

import org.asteriskjava.live.ManagerCommunicationException;

public class HelloLive extends TimerTask{
	private AsteriskServer asteriskServer;
		public HelloLive()
	{
		asteriskServer = new DefaultAsteriskServer("localhost", "admin", "asterisk@321");

	}

	public void run() throws ManagerCommunicationException{
		for (AsteriskChannel asteriskChannel : asteriskServer.getChannels()){

			System.out.println(asteriskChannel);
		}

		for (AsteriskQueue asteriskQueue : asteriskServer.getQueues())
		{
			System.out.println(asteriskQueue);
		}

		for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms())
		{
			System.out.println(meetMeRoom);
		}
	}
		
	public static void main(String[] args) throws Exception
	{
	Timer timer = new Timer();
    	timer.schedule(new HelloLive(), 0, 1000);

	}

	

}