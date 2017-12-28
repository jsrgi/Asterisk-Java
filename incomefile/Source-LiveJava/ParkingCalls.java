import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;



public class ParkingCalls 

{
    
     
        public ParkingCalls(ManagerConnection c) {
       
    	try{
    	//c.login();	
       

        CommandAction action;
        CommandResponse response;
        List<String> list = new ArrayList<String>();
	String[] str;
      action = new CommandAction();
        action.setCommand("parkedcalls show");
	response = (CommandResponse) c.sendAction(action);
	BufferedWriter out = null;
	  out = new BufferedWriter(new FileWriter("ParkingCalls.txt",false));
	
        list = response.getResult();
       //System.out.println(list.size());
        str=new String[list.size()];
        int i = 1;
        while ( i <list.size()-4)
        {
        	str[i]=list.get(i+1);
    		out.write(str[i].toCharArray());
    		out.newLine();
            i++;
        }
        out.flush();
        out.close();
       	//c.logoff();       
    
    }
    catch(Exception e){System.out.println(e.getMessage());}
    	  	
    	    }

		
   	
}