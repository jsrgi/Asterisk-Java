
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.ManagerCommunicationException;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.MeetMeUser;

public class MeetmeRoom {
	//private AsteriskServer asteriskServer;
	private BufferedWriter out = null;
	private ArrayList<String> mChannelList = null;

	public  MeetmeRoom(AsteriskServer asteriskServer) throws ManagerCommunicationException{
		try {
			out = new BufferedWriter(new FileWriter("MeetmeRoom.txt", false));
			
			for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms()) {
				ArrayList<MeetMeUser> mMeetMeUserList = new ArrayList<>(meetMeRoom.getUsers());
				for (int i = 0; i < mMeetMeUserList.size(); i++) {
					mChannelList = new ArrayList<>();
					mChannelList.add(mMeetMeUserList.get(i).getRoom().getRoomNumber().toString());
					mChannelList.add(mMeetMeUserList.get(i).getChannel().getName().toString());
					out.write(mChannelList.toString());
					out.newLine();
				}

			}
			out.flush();
			out.close();

		} catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}

	
	
	

}