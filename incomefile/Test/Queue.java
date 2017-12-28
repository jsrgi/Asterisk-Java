
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.live.ManagerCommunicationException;



public class Queue extends TimerTask{
	private AsteriskServer asteriskServer;
	
	
	public Queue()
	{
		asteriskServer = new DefaultAsteriskServer("localhost", "admin", "asterisk@321");

	}

	public void run() throws ManagerCommunicationException{
		try
		
		{
			
			BufferedWriter out = null;
		  out = new BufferedWriter(new FileWriter("Queue.txt",false));
		
		
		 
		ArrayList<AsteriskQueue> asteriskQueue = new ArrayList<>(asteriskServer.getQueues());
			ArrayList<String> map = null;
			for(int i =0;i<asteriskQueue.size();i++)
			{
				
				for(int k=0;k<asteriskQueue.get(i).getEntries().size();k++){
					map = new ArrayList<>();
					map.add(asteriskQueue.get(i).getName().toString());
					map.add(asteriskQueue.get(i).getEntries().get(k).getChannel().getName().toString());
					System.out.println("Queue"+map.toString());
					out.write(map.toString());
					out.newLine();
				}
			}
			
			/*ArrayList<AsteriskQueueMember> mMeetMeUserList = new ArrayList<>(asteriskQueue.getMembers());
			for (int i = 0; i < mMeetMeUserList.size(); i++) {
				mChannelList = new ArrayList<>();
				mChannelList.add(mMeetMeUserList.get(i).getQueue().getName().toString());
				mChannelList.add(mMeetMeUserList.get(i).getLocation().toString());
				out.write(mChannelList.toString());
				out.newLine();*/
				//System.out.println("Queue"+map.toString());
			//}
		
		 out.flush();
	        out.close();


		/*for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms())
		{
			System.out.println(meetMeRoom);
		}*/
	}catch(Exception e)
		{
		
		}
	}
	public static void main(String[] args) throws Exception
	{
	Timer timer = new Timer();
    	timer.schedule(new Queue(), 0, 2000);
		//new Queue().run();

	}

	

}