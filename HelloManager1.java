import java.io.IOException;

import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.*;
import org.asteriskjava.manager.response.ManagerResponse;

public class HelloManager1 {
private ManagerConnection managerConnection;

public HelloManager1() throws IOException {
ManagerConnectionFactory factory = new ManagerConnectionFactory("localhost", "admin", "asterisk@321");
this.managerConnection = factory.createManagerConnection();
} 

public void run() throws IOException, AuthenticationFailedException, TimeoutException {
OriginateAction originateAction;
ManagerResponse originateResponse;
originateAction = new OriginateAction();
originateAction.setChannel("SIP/5220"); 
originateAction.setContext("outgoing");
originateAction.setExten("outbound");
originateAction.setAsync(false);
originateAction.setPriority(new Integer(1));
originateAction.setTimeout(new Integer(3000));
// connect to Asterisk and log in

managerConnection.login();
// send the originate action and wait for a maximum of 30 seconds for Asterisk
// to send a reply
originateResponse = managerConnection.sendAction(originateAction, 300000);
// print out whether the originate succeeded or not
System.out.println("originate response is: "+originateResponse.getResponse());
System.out.println("originate MEssage is: "+originateResponse.getMessage());
System.out.println("originate event list is: "+originateResponse.getEventList());
System.out.println("originate attribute is: "+originateResponse.getAttribute("hello"));
System.out.println("originate server is: "+originateResponse.getServer());
System.out.println("originate data received is: "+originateResponse.getDateReceived());






// and finally log off and disconnect
managerConnection.logoff();
}

public static void main(String[] args) throws Exception {
HelloManager1 helloManager;
helloManager = new HelloManager1();
helloManager.run();

}
}
