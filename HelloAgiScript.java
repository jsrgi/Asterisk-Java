/**
 * 
 */

/**
 * @author opensource
 *
 */
import org.asteriskjava.fastagi.*;
import org.asteriskjava.live.Extension;
import org.asteriskjava.manager.action.ParkAction;
import org.asteriskjava.manager.event.*;
public class HelloAgiScript extends BaseAgiScript
{    
    public void service(AgiRequest request, AgiChannel channel)
            throws AgiException
    {
        // Answer the channel...
    	
        answer();
        // ...say hello...
        streamFile("welcome");
        streamFile("tt-monkeys");
        System.out.println(" working ");
        // ...and hangup.
        hangup();  
        TransferEvent te=new TransferEvent(getChannel());
        System.out.println("trannfer events"+te.toString());
        ParkAction pa=new ParkAction();
        System.out.println("parked information"+pa.toString());
        Extension ex=new Extension("default", "777", 1);
        System.out.println("Extension details" +ex.toString());
    }        
}
