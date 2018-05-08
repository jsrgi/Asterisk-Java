import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.EventsAction;
import org.asteriskjava.manager.action.ParkAction;
import org.asteriskjava.manager.event.HoldEvent;
import org.asteriskjava.manager.event.HoldedCallEvent;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;
public class TransCall {
	private ManagerConnection mc;
	Scanner s=new Scanner(System.in);
	String c1,c2;
	ManagerResponse mr=new ManagerResponse();
	public TransCall() {
		mc=new DefaultManagerConnection("localhost","admin","asterisk@321");
	}
	public void run(String args1,String args2) throws InterruptedException{
		try
		    {
		        mc.login();
		       

		        CommandAction action;
		        CommandResponse response;
		        List<String> list = new ArrayList<String>();
			String[] str;
			String[] str2;
			String s1,s2,sub;
		      action = new CommandAction();
		        action.setCommand("core show channels verbose");
			response = (CommandResponse) mc.sendAction(action);

		        list = response.getResult();
		        //System.out.println(list.size()-3);
		        str=new String[list.size()-3];
		        str2=new String[list.size()-3];
		        int i = 0;
		        while ( i <list.size()-3)
		        {
				str[i]=list.get(i);
		            //System.out.println(str[i]);
		            i++;
		        }
				//System.out.println(str[0]);
		        i=0;
		        do
		        {
		        	s1=str[0];
		    		//s2=str[i];
		    		int x=s1.indexOf("Extension");
		    		int y=s1.indexOf("Prio");
		    		sub=str[i].substring(x,y-1).trim();
		    		//System.out.println(sub);
		    		str2[i]=sub;
		    		i++;
		        }while ( i <list.size()-3);
				

		        //System.out.println("Enter extension to disconnect");
			String c1=args1;
			String c2=args2;
			
			int j=0;
			
			while(j< str2.length){
			if(c1.equals(str2[j]))
			{
				int ext_place=j;
				s1=str[0];
				s2=str[ext_place];
				int x=s1.indexOf("Channel");
				int y=s1.indexOf("Context");
				sub=s2.substring(x,y-1).trim();
				//System.out.println(sub);
				c1=sub;
				
			}
			if(c2.equals(str2[j]))
			{
				int ext_place=j;
				s1=str[0];
				s2=str[ext_place];
				int x=s1.indexOf("Channel");
				int y=s1.indexOf("Context");
				sub=s2.substring(x,y-1).trim();
				//System.out.println(sub);
				c2=sub;
				
			}
			j++;
			}


	        
			//System.out.println("Enter channel 2");
			//c2=s.next();
			ParkAction pa=new ParkAction(c1, c2, 2000);
			mr=mc.sendAction(pa);
			System.out.println(mr.getMessage());
			HoldEvent he=new HoldEvent(pa);
			System.out.println(he.toString());
		mc.logoff();
	} catch (IllegalStateException | IOException | AuthenticationFailedException | TimeoutException e) {
		
		e.printStackTrace();
	}
	}

	public static void main(String[] args) throws InterruptedException {
		
TransCall hc=new TransCall();
hc.run(args[0],args[1]);
	}

}
