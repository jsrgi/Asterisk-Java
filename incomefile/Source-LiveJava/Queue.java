
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskQueueMember;
import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.ManagerCommunicationException;


public class Queue  {
		
	private BufferedWriter out = null;
	private ArrayList<String> mChannelList = null;
	public Queue(AsteriskServer asteriskServer) throws ManagerCommunicationException{
		try
		{
			
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
			}		 out.flush();
	        out.close();


	}catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}

			
}