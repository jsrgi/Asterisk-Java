import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.internal.AsteriskServerImpl;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.OriginateAction;
//import org.asteriskjava.manager.response.ManagerResponse;

public class PhoneUtility {

private static ManagerConnection instanceManagerConnection;

private static ManagerConnection getManagerConnectionInstance() {
if (instanceManagerConnection == null) {
ManagerConnectionFactory factory = new ManagerConnectionFactory("192.132.0.01","admin","amp111");
instanceManagerConnection = factory.createManagerConnection();
}
return instanceManagerConnection;
}

public static boolean phoneCall() {
try {
ManagerConnection managerConnection = getManagerConnectionInstance();
OriginateAction originateAction;
//ManagerResponse originateResponse;

originateAction = new OriginateAction();
originateAction.setChannel("SIP/ivan");
originateAction.setCallerId("1234");
originateAction.setContext("ivr-ext");
originateAction.setExten("1235");
originateAction.setPriority(new Integer(1));
originateAction.setActionId("2");
originateAction.setTimeout(300000l);
originateAction.setAsync(new Boolean(false));

// connect to Asterisk and log in
managerConnection.login();

AsteriskServerImpl asteriskServer = new AsteriskServerImpl(managerConnection);
AsteriskChannel asteriskChannel = asteriskServer.originate(originateAction);

// Play a prompt by text to speach

Character dtmf = asteriskChannel.getDtmfSent();
if (dtmf.equals("1")) {
managerConnection.logoff();
return true;
} else {
managerConnection.logoff();
return false;
}

} catch (Exception e) {
System.out.println(e);
return false;
}
}
public static void main(String ars[])
{
	//PhoneUtility pu= new PhoneUtility();
	PhoneUtility.phoneCall();
	PhoneUtility.getManagerConnectionInstance();
}
}