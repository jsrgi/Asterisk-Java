
import org.asteriskjava.manager.AbstractManagerEventListener;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.RedirectAction;
import org.asteriskjava.manager.event.MeetMeJoinEvent;
import org.asteriskjava.manager.event.MeetMeLeaveEvent;

public class AMIListenerTest extends AbstractManagerEventListener {

	public AMIListenerTest() {
		// Instantiate the factory
		ManagerConnectionFactory factory = new ManagerConnectionFactory(
				"localhost", "admin", "asterisk@321");
		// Retrieve the connection from the factory
		ManagerConnection managerConnection = factory.createManagerConnection();
		try {
			// login to Asterisk
			managerConnection.login();
			// Add this object as listener to the connection
			managerConnection.addEventListener(this);
managerConnection.logoff();


		} catch (Exception e) {
			// Manage exception
			e.printStackTrace();
		}
	}

	@Override
	protected void handleEvent(MeetMeJoinEvent event) {
		System.out.println(event);
	}

	@Override
	protected void handleEvent(MeetMeLeaveEvent event) {
		System.out.println(event);
	}

	public static void main(String[] args) {
		new AMIListenerTest();
		while (true) {
		}
		
		


	}
}