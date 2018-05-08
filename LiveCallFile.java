import java.io.BufferedWriter;
//import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.PrintStream;
//import java.lang.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;



public class LiveCallFile extends TimerTask
{
    private ManagerConnection c;
Scanner s=new Scanner(System.in);

    public LiveCallFile() throws Exception
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
                "localhost", "admin", "asterisk@321");
        c =  factory.createManagerConnection();
    }
    
    public void run()
    {
    	try{
    	
        c.login();

        CommandAction action;
        CommandResponse response;
        List<String> list = new ArrayList<String>();
	String[] str;
      action = new CommandAction();
        action.setCommand("core show channels verbose");
	response = (CommandResponse) c.sendAction(action);
	BufferedWriter out = null;
  out = new BufferedWriter(new FileWriter("output.txt"));
        list = response.getResult();
       // System.out.println(list.size());
        str=new String[list.size()];
        int i = 0;
        while ( i <list.size()-4)
        {
        	str[i]=list.get(i+1);
        	
        	     
     out.write("["+str[i].toString()+"]");
    out.newLine();
            i++;
        }
        out.flush();
        out.close();
        
        
		        c.logoff();
    
    }
    catch(Exception e){}
    }
    
    public static void main(String[] args) throws Exception
    {
    	Timer timer = new Timer();
    	
    	timer.schedule(new LiveCallFile(), 0, 1000);
    }
}