import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
//import org.asteriskjava.manager.action.EventsAction;
import org.asteriskjava.manager.action.ParkAction;

//import org.asteriskjava.manager.event.HoldedCallEvent;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;
public class HoldCall {
	private ManagerConnection mc;
	Scanner s=new Scanner(System.in);
	String c1,c2;
	ManagerResponse mr=new ManagerResponse();
	public HoldCall() {
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
			/*String[] str2;
			String s1,s2,sub;*/
		      /*action = new CommandAction();
		        action.setCommand("core show channels verbose");
			response = (CommandResponse) mc.sendAction(action);

		        list = response.getResult();*/
		        action = new CommandAction();
		        action.setCommand("core show channels concise");
			response = (CommandResponse) mc.sendAction(action);

		        list = response.getResult();
		       System.out.println(list.toString());
		        str=new String[list.size()];
		        for(int k =0;k<list.size();k++){
			    	  str = list.get(k).split("!");
			    	 
			    	  if (!(str[0].substring(0,5)).equals("Local"))  {
			    	
			    		  if(str[7].endsWith(args1)){
			    		 
			    			  c1=str[0];	
			    		 
			    	 } 
			      }
		        }
		       /* //System.out.println(list.size()-3);
		        str=new String[list.size()-3];
		        str2=new String[list.size()-3];
		        ArrayList<String> map = new ArrayList<>();
		        int i = 0;
		        while ( i <list.size()-3)
		        {
		        	str[i]=list.get(i);
			           // System.out.println(str[i]);
			            if (!(str[i].substring(0,5)).equals("Local"))	{
							
							map.add(str[i].toString());
							
						}
		            i++;
		        }
				//System.out.println(str[0]);
		        i=0;
		        do
		        {
		        	s1=map.get(0);
		    		//s2=str[i];
		    		int x=s1.indexOf("CallerID");
    		int y=s1.indexOf("Duration");
		    		sub=map.get(i).substring(x,y-1).trim();
		    		//System.out.println(sub);
		    		str2[i]=sub;
		    		i++;
		        }while ( i <map.size());
				

		        //System.out.println("Enter extension to disconnect");
			String c1=args1;
			
			
			
			int j=0;
			
			while(j< str2.length){
			if(c1.equals(str2[j]))
			{
				int ext_place=j;
				s1=map.get(0);
				s2=map.get(ext_place);
				int x=s1.indexOf("Channel");
				int y=s1.indexOf("Context");
				sub=s2.substring(x,y-1).trim();
				//System.out.println(sub);
				c1=sub;
				
			}
			j++;
			}
//int k=0;
//			
//			while(k< str2.length){
//			if(c2.equals(str2[k]))
//			{
//				int ext_place=k;
//				s1=map.get(0);
//				s2=map.get(ext_place);
//				int x=s1.indexOf("Channel");
//				int y=s1.indexOf("Context");
//				sub=s2.substring(x,y-1).trim();
//				//System.out.println(sub);
//				c2=sub;
//				
//			}
//			k++;
//			}




	        
			//System.out.println("Enter channel 2");
			//c2=s.next();
*/			ParkAction pa=new ParkAction(c1, c1,100000);
			mr=mc.sendAction(pa);
			System.out.println(mr.getMessage());
			////HoldEvent he=new HoldEvent(pa);
			//System.out.println(he.toString());
		mc.logoff();
	} catch (IllegalStateException | IOException | AuthenticationFailedException | TimeoutException e) {
		
		e.printStackTrace();
	}
	}

	public static void main(String[] args) throws InterruptedException {
		
HoldCall hc=new HoldCall();
hc.run(args[0],args[1]);
	}

}
