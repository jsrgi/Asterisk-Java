import java.util.List;

import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.internal.AsteriskServerImpl;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.event.PeerEntryEvent;

public class LiveTest {

	public static void main(String[] args) {
		// Retrieve the factory with connection parameters
		ManagerConnectionFactory factory = new ManagerConnectionFactory(
				"localhost", "admin", "asterisk@321");
		// Retrieve the connection from the factory
		ManagerConnection managerConnection = factory.createManagerConnection();
		try {
			// login to Asterisk
			managerConnection.login();
			// Instantiate a new AsteriskServer
			AsteriskServerImpl asteriskServer = new AsteriskServerImpl(
					managerConnection);
			// Retrieve the list of configured peers
			List<PeerEntryEvent> peerEntries1 = asteriskServer.getPeerEntries();
			PeerEntryEvent peerToCall = null;
			// Iterate over peers
			for (PeerEntryEvent peerEntry : peerEntries1) {
				String status = peerEntry.getStatus();
				// Select the first one connected and exit the loop
				if (status != null && status.contains("OK")) {
					peerToCall = peerEntry;
					break;
				}
			}
			// If no peer is connected exit from method
			if (peerToCall == null) {
				return;
			}
			String channelName = peerToCall.getChannelType();
			channelName += "/" + peerToCall.getObjectName();
			System.out.println("Calling " + channelName);
			AsteriskChannel asteriskChannel = 
                           asteriskServer.originateToExtension(channelName, "from-internal",   "1234", 1, 10000);
			Thread.sleep(10000);
			System.out.println("Playing '1' on " + channelName);
			asteriskChannel.playDtmf("1");
		} catch (Exception e) {
			// Manage exception
			e.printStackTrace();
		}
	}
}