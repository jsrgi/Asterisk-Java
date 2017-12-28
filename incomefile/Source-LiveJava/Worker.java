import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;

public class Worker extends TimerTask implements Runnable
{ 
	public ManagerConnection c;
	public AsteriskServer asteriskServer;
Scanner s=new Scanner(System.in);

public Worker() throws Exception
{
    ManagerConnectionFactory factory = new ManagerConnectionFactory(
            "localhost", "admin", "asterisk@321");
    c =  factory.createManagerConnection();
     asteriskServer=new DefaultAsteriskServer("localhost", "admin", "asterisk@321");
     
if (!c.getState().equals("CONNECTED")) {
			
			c.login();
			
		}
		
		System.out.println("STATUS>>>>>>>>>>>>>>>>>>>>>>>" + c.getState());     
     
}

  public static void main (String[] args) throws Exception  
  {  
    
	  Timer timer = new Timer();
  	
  	
  	timer.schedule(new Worker(), 0, 2000);  
  	
    //Thread thread = new Thread(work);  
    //thread.start();  
  }  
 
  public void run()   
  {  
	  new LiveCallFile(c);
	     
	     new MeetmeRoom(asteriskServer);
	     
	     new ParkingCalls(c);
	     
	     new Queue(asteriskServer); 
	     new MeetmeTime(c);
    //c.logoff();
    
  }  
}  