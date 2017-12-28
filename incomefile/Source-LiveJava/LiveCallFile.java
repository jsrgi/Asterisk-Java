import java.io.BufferedWriter;
//import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.PrintStream;
//import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;



public class LiveCallFile
{
    
    public  LiveCallFile(ManagerConnection c)
    {
    	try{
//c.login();
    	
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
        	
        	if (!(str[0].substring(0,5)).equals("Local")){     
     out.write(str[i].toString());
    out.newLine();
}
            i++;
        }
        out.flush();
        out.close();
 //c.logoff();   
    }
    catch(Exception e){System.out.println(e.getMessage());}
    }

	
	 
    
}