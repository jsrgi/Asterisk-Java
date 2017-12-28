import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;

public class PeerInfo {
private ManagerConnection mc;
	
	String c1,c2;
	ManagerResponse mr=new ManagerResponse();
	
	public  PeerInfo() {
		// TODO Auto-generated constructor stub
	 
		mc=new DefaultManagerConnection("localhost","admin","asterisk@321");
	}
	public void run(String extension) throws InterruptedException{
		try
		    {
		        mc.login();
		       

		        CommandAction action;
		        CommandResponse response;
		        List<String> list = new ArrayList<String>();
			
		      action = new CommandAction();
		        action.setCommand("sip show peer "+extension);
				response = (CommandResponse) mc.sendAction(action);
				BufferedWriter out = null;
				  out = new BufferedWriter(new FileWriter("PeerInfo.txt"));
				        list = response.getResult();
				       // System.out.println(list.size());
				      String[]  str=new String[list.size()];
				        int i = 0;
				        while ( i <list.size()-4)
				        {
				        	str[i]=list.get(i+1);
				        	
				        	     
				     out.write(str[i].toString());
				    out.newLine();
				            i++;
				        }
				        out.flush();
				        out.close();
				        
				
			
		mc.logoff();
	} catch (IllegalStateException | IOException | AuthenticationFailedException | TimeoutException e) {
		
		e.printStackTrace();
	}
	}

	public static void main(String[] args) throws InterruptedException {
		
		PeerInfo hc=new PeerInfo();
hc.run(args[0]);
	}

}

