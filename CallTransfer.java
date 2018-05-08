//import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.util.*;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;
//import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.RedirectAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class CallTransfer {
	ManagerConnection f;
	Scanner s=new Scanner(System.in);
	public CallTransfer() throws IllegalStateException, IOException, AuthenticationFailedException, TimeoutException {
		f = new DefaultManagerConnection("localhost", "admin", "asterisk@321");
		// Retrieve the connection from the factory
		f.login();
	}
		public void run() throws IllegalArgumentException, IllegalStateException, IOException, TimeoutException, AuthenticationFailedException{
			
				// login to Asterisk
				
		CommandAction action;
        CommandResponse response;
        List<String> list = new ArrayList<String>();
	

      action = new CommandAction();
        action.setCommand("core show channels");
	response = (CommandResponse) f.sendAction(action);

        list = response.getResult();
        System.out.println(list.size());
        int i = 0;
        while ( i <list.size())
        {

            System.out.println(list.get(i));
            i++;
        }
	System.out.println("Enter channel name to redirect");
	String channel_name=s.next();

		RedirectAction originateAction = new RedirectAction();

		originateAction.setChannel(channel_name);
		originateAction.setContext("from-internal");
		originateAction.setExten("1234");
		originateAction.setPriority(1);//setPriority("1");

		ManagerResponse originateResponse = f.sendAction(originateAction);
		System.out.println(originateResponse);
		f.logoff();
			
	
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalStateException, IOException, TimeoutException, AuthenticationFailedException {
		// TODO Auto-generated method stub
			CallTransfer ct=new CallTransfer();
				ct.run();
	}

}
