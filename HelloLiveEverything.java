
import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskQueueEntry;
import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.AsteriskServerListener;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.live.ManagerCommunicationException;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.MeetMeUser;
import org.asteriskjava.live.internal.AsteriskAgentImpl;

import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;
import java.beans.PropertyChangeEvent;

public class HelloLiveEverything extends TimerTask implements AsteriskServerListener, PropertyChangeListener
{
    private AsteriskServer asteriskServer;

    public HelloLiveEverything()
    {
        asteriskServer = new DefaultAsteriskServer("localhost", "admin", "asterisk@321");
    }

    public void run() throws ManagerCommunicationException
    {
        // listen for new events
        asteriskServer.addAsteriskServerListener(this);

        // add property change listeners to existing objects
        for (AsteriskChannel asteriskChannel : asteriskServer.getChannels())
        {
            System.out.println(asteriskChannel);
            asteriskChannel.addPropertyChangeListener(this);
        }

        for (AsteriskQueue asteriskQueue : asteriskServer.getQueues())
        {
            System.out.println(asteriskQueue);
            for (AsteriskQueueEntry asteriskChannel : asteriskQueue.getEntries())
            {
                asteriskChannel.addPropertyChangeListener(this);
            }
        }

        for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms())
        {
            System.out.println(meetMeRoom);
            for (MeetMeUser user : meetMeRoom.getUsers())
            {
                user.addPropertyChangeListener(this);
            }
        }
    }

    public void onNewAsteriskChannel(AsteriskChannel channel)
    {
        System.out.println(channel);
        channel.addPropertyChangeListener(this);
    }

    public void onNewMeetMeUser(MeetMeUser user)
    {
        System.out.println(user);
        user.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        System.out.println(propertyChangeEvent);
    }
    public void onNewAgent(AsteriskAgentImpl agent) {
		
    	 System.out.println(agent);
         agent.addPropertyChangeListener(this);
	}

	
	public void onNewQueueEntry(AsteriskQueueEntry entry) {
		
		 System.out.println(entry);
	        entry.addPropertyChangeListener(this);
		
	}
    public static void main(String[] args) throws Exception
    {
    	Timer timer = new Timer();
	timer.schedule(new HelloLiveEverything(), 0, 5000);

    }

	
	

	
}