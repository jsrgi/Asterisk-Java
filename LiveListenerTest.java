
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.asteriskjava.live.AbstractAsteriskServerListener;
import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueueEntry;
import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.live.MeetMeUser;
import org.asteriskjava.live.internal.AsteriskAgentImpl;

public class LiveListenerTest extends AbstractAsteriskServerListener implements
		PropertyChangeListener {

	public LiveListenerTest() {
		AsteriskServer asteriskServer = new DefaultAsteriskServer("localhost",   "admin", "asterisk@321");
		asteriskServer.addAsteriskServerListener(this);
	}

	public void onNewAsteriskChannel(AsteriskChannel channelParam) {
		System.out.println(channelParam);
		channelParam.addPropertyChangeListener(this);
	}

	public void onNewMeetMeUser(MeetMeUser userParam) {
		System.out.println(userParam);
		userParam.addPropertyChangeListener(this);
	}

	public void onNewAgent(AsteriskAgentImpl agentParam) {
		System.out.println(agentParam);
		agentParam.addPropertyChangeListener(this);
	}

	public void onNewQueueEntry(AsteriskQueueEntry entryParam) {
		System.out.println(entryParam);
		entryParam.addPropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		System.out.println(propertyChangeEvent);
	}

	public static void main(String[] args) {
		new LiveListenerTest();
		while (true) {
		}
	}
}