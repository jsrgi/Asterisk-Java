import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.asteriskjava.live.internal.AsteriskServerImpl;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
//import org.asteriskjava.manager.event.HoldedCallEvent;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;
public class CallTransferBoth {
	private ManagerConnection c;
    AsteriskServerImpl asteriskServer ;
Scanner s=new Scanner(System.in);

    public CallTransferBoth() throws Exception
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
                "localhost", "admin", "asterisk@321");
        c =  factory.createManagerConnection();
       asteriskServer = new AsteriskServerImpl(c);
    }
	public void run(String args1,String extension) throws InterruptedException{
		try
		    {
		        c.login();
		       

		        CommandAction action;
		        CommandResponse response;
		        ManagerResponse response1=null;
		        List<String> list = new ArrayList<String>();
			String[] str;
			
			String c1=null;
			
			action = new CommandAction();
		     action.setCommand("core show channels concise");
			response = (CommandResponse) c.sendAction(action);
		
		       list = response.getResult();
//			list.add("DAHDI/i1/98652016000-298!from-internal!!1!Up!AppDial!(Outgoing Line)!998652016000!!!3!32!SIP/9111-000003ea!1488266371.2205");
//			list.add("SIP/9111-000003ea!from-internal!998652016000!3!Up!Dial!DAHDI/g0/98652016000!5554!!!3!32!DAHDI/i1/98652016000-298!1488266371.2204");
//			list.add("SIP/1145-000003e3!from-internal!908066326028!3!Up!Dial!DAHDI/g0/08066326028!5554!!!3!1602!DAHDI/i1/08066326028-291!1488264802.2190");
//			list.add("DAHDI/i1/08066326028-291!from-internal!!1!Up!AppDial!(Outgoing Line)!908066326028!!!3!1602!SIP/1145-000003e3!1488264802.2191");

		        //System.out.println(list.toString());
		        
			str=new String[list.size()];
		     
		        //System.out.println(list.size());

		        for(int k =0;k<list.size();k++){
		        	
			    	  str = list.get(k).split("!");
			    	 
			    	  if (!(str[0].substring(0,5)).equals("Local"))  {
			    		  
			    		  if(str[0].substring(0, 2).equals("SI")){
			    			
						 int position = str[0].indexOf("-");
							
			    			  if(str[0].substring(4, position).endsWith(args1)) 
			    				  c1=str[0];
			    		  }
			    		  else if(str[0].substring(0, 2).equals("DA")){
					    			 
			    			  int position = str[0].indexOf("-");
				    			  
			    			  if(str[0].substring(8, position).endsWith(args1))
				    				  c1=str[0];
				    		  }  
			    		  }
			    	
			    		  //if(str[7].endsWith(args1)){
			    		 
			    		// c1=str[0];	
			    		 
			    	// } 
			      }
		        
		        System.out.println(c1);
			
			action.setCommand("channel redirect "+c1+" from-internal,"+extension+",1");
			response1 = (ManagerResponse) c.sendAction(action);
			System.out.println(response1.getResponse());
			
		c.logoff();
	} catch (IllegalStateException | IOException | AuthenticationFailedException | TimeoutException e) {
		
		e.printStackTrace();
	}
	}

	public static void main(String[] args) throws Exception {
		
		CallTransferBoth hc=new CallTransferBoth();
		hc.run(args[0],args[1]);
	}

}
