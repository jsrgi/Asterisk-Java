
import java.util.Scanner;
import org.asteriskjava.live.internal.AsteriskServerImpl;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class OriginateCall 
	{
	    private ManagerConnection c;
	    AsteriskServerImpl asteriskServer ;
	Scanner s=new Scanner(System.in);

	    public OriginateCall() throws Exception
	    {
	        ManagerConnectionFactory factory = new ManagerConnectionFactory(
	                "localhost", "admin", "asterisk@321");
	        c =  factory.createManagerConnection();
	       asteriskServer = new AsteriskServerImpl(c);
	    }

	    public void run(String channel_name,String extension,String type) throws Exception
	    {
	        c.login(); 
	        
	        ManagerResponse originateResponse=null;
	  
//for originate action
	      if(type.equals("SIP")){
	     OriginateAction oa=new OriginateAction();
	     oa.setChannel("SIP/"+channel_name);
	     oa.setActionId(channel_name);
	     oa.setExten(extension);
	     oa.setAsync(true);
	     oa.setCallerId(extension);
	     oa.setData(channel_name);
	         
	     originateResponse = c.sendAction(oa,30000);
		
	      }
	      else
	      {
	    	 OriginateAction oa=new OriginateAction();
	 	     oa.setChannel("DAHDI/i1/0"+channel_name);
	 	     oa.setActionId(channel_name);
	 	     oa.setExten(extension);
	 	     oa.setAsync(true);
	 	     oa.setCallerId(extension);
	 	     oa.setData(channel_name);
	 	         
	 	     originateResponse = c.sendAction(oa,30000);
	      }
		System.out.println(originateResponse.getResponse()+"\n");
		System.out.println(originateResponse.getMessage());
		
	        c.logoff();
	    }
	    	
	    
	  
	public static void main(String[] args) throws Exception {
		
		OriginateCall red=new OriginateCall(); 
		red.run(args[0],args[1],args[2]);

}
}
