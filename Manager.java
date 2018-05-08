import java.io.FileOutputStream;
import java.io.PrintStream;
//import java.lang.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;



public class Manager extends TimerTask
{
    private ManagerConnection c;
Scanner s=new Scanner(System.in);

    public Manager() throws Exception
    {
    	ManagerConnectionFactory factory = new ManagerConnectionFactory(
    	        "localhost", "admin", "asterisk@321");
    	c =  factory.createManagerConnection();
    	c.login();
    }	
     
        public void run() {
       
    	try{
    	
       

        CommandAction action;
        CommandResponse response;
        List<String> list = new ArrayList<String>();
	String[] str;
      action = new CommandAction();
        action.setCommand("core show channels verbose");
	response = (CommandResponse) c.sendAction(action);
	PrintStream out = new PrintStream(new FileOutputStream("output.txt",false));
        list = response.getResult();
       // System.out.println(list.size());
        str=new String[list.size()];
        int i = 0;
        while ( i <list.size()-4)
        {
        	str[i]=list.get(i+1);
        	//System.out.println(str[i]);
      
    		System.setOut(out);
    		//out.append(str[i]+"\n");
    		byte[] b=str[i].toString().getBytes();
    		out.write(b);
    		out.println();
    		 out.flush();
            i++;
        }
        out.flush();
        out.close();
        
        
		       
    
    }
    catch(Exception e){}
    	
    	
    	c.logoff();	
    }
    
    public static void main(String[] args) throws Exception
    {
    	
    	Timer timer = new Timer();
        timer.schedule(new Manager(),0,1000);

    }
	
	
}