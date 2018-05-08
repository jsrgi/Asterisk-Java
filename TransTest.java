import java.lang.*;
import java.util.*;

import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;
//import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.ManagerAction;
import org.asteriskjava.manager.action.RedirectAction;
import org.asteriskjava.manager.response.CommandResponse;
public class TransTest {
	private ManagerConnection c;
	Scanner s=new Scanner(System.in);

	    public TransTest() throws Exception
	    {
	       c=new DefaultManagerConnection("localhost", "admin", "asterisk@321");
	       
	    }

	    public void run() throws Exception
	    {
	        c.login();

	        CommandAction action;
	        CommandResponse response;
	        
	        List<String> list = new ArrayList<String>();
		

	      action = new CommandAction();
	      RedirectAction ra=new RedirectAction("SIP/2234", "from-internal", "1234", 1);
	      
	       action.setActionId("1425862");//setActionId(ra.setActionId("1452368"));
		response = (CommandResponse) c.sendAction(action);

	        list = response.getResult();
	        System.out.println(list.size());
	        int i = 0;
	        while ( i <list.size())
	        {

	            System.out.println(list.get(i));
	            i++;
	        }
		System.out.println("Enter channel name to disconnect");
		String channel_name=s.next();
	        action.setCommand("channel request hangup "+channel_name);
	        response = (CommandResponse) c.sendAction(action);
		//list = response.getResult();
	        System.out.println(response.getResponse());
	        System.out.println(response.getResult());
	              
	        action.setCommand("core show channels");
		response = (CommandResponse) c.sendAction(action);

	        list = response.getResult();
	        System.out.println(list.size());
	        i=0;
	                while ( i <list.size())
	        {

	            System.out.println(list.get(i));
	            i++;
	        }

	        c.logoff();
	    }

	    public static void main(String[] args) throws Exception
	    {
	        new TransTest().run();
	    }
}
