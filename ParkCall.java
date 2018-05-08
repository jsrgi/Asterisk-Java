import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.DefaultManagerConnection;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.action.ParkAction;
import org.asteriskjava.manager.action.SipShowPeerAction;
import org.asteriskjava.manager.event.HoldEvent;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;
public class ParkCall {
	private ManagerConnection mc;
	Scanner s=new Scanner(System.in);
	String c1,c2;
	ManagerResponse mr=new ManagerResponse();
	public ParkCall() {
		mc=new DefaultManagerConnection("localhost","admin","asterisk@321");
	}
	public void run() throws InterruptedException{
		try {
			mc.login();
			CommandAction action;
	        CommandResponse response;
	        List<String> list = new ArrayList<String>();
		

	      action = new CommandAction();
	        action.setCommand("core show channels");
		response = (CommandResponse) mc.sendAction(action);

	        list = response.getResult();
	        System.out.println(list.size());
	        int i = 0;
	        while ( i <list.size())
	        {

	            System.out.println(list.get(i));
	            i++;
	        }
	        
	        OriginateAction originateAction;
	       // ManagerResponse originateResponse;
	       // HoldEvent event = new HoldEvent(c2);
	        SipShowPeerAction peer = new SipShowPeerAction();
	        peer.setPeer("SIP/5221");
	        System.out.println("Enter channel 1");
			c1=s.next();
	       // mc.addEventListener(event);
	        originateAction = new OriginateAction();
	        ParkAction park = new ParkAction();
	        originateAction.setChannel(c1);
	        originateAction.setCallerId("<5221>");
	        originateAction.setContext("from-internal");
	        originateAction.setPriority(new Integer(1));
	        originateAction.setExten("1234");

	        
	       // mc.addEventListener(event);
	        mr = mc.sendAction(peer);

	        mr = mc.sendAction(originateAction, 30000);
	        Thread.sleep(3000);
	        park.setActionId(originateAction.getActionId());

	       
			
			//ManagerAction ma;
			System.out.println("Enter channel 1");
			c1=s.next();
			System.out.println("Enter channel 2");
			c2=s.next();
			ParkAction pa=new ParkAction(c1, c2);
			mr=mc.sendAction(pa);
			System.out.println("Response is"+mr);
			HoldEvent he=new HoldEvent(c1);
			if(he.isHold()){
				System.out.println("event data received "+he.toString());
			}
			mc.logoff();
		} catch (IllegalStateException | IOException | AuthenticationFailedException | TimeoutException e) {
			
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ParkCall pc=new ParkCall();
		pc.run();
	}

}
