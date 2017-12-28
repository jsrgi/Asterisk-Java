import java.util.ArrayList;
import java.util.List;
//import java.lang.*;
import java.util.*;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;



public class HangUp1
{
    private ManagerConnection c;
Scanner s=new Scanner(System.in);

    public HangUp1() throws Exception
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
                "localhost", "admin", "asterisk@321");
        c =  factory.createManagerConnection();
    }

    public void run(String Called_id) throws Exception
    {
        c.login();

        CommandAction action;
        CommandResponse response;
        List<String> list = new ArrayList<String>();
	String[] str;
	/*String[] str2;
	String s1,s2,sub;*/
	String channel_name=null;
      action = new CommandAction();
        action.setCommand("core show channels concise");
	response = (CommandResponse) c.sendAction(action);

        list = response.getResult();
       System.out.println(list.toString());
        str=new String[list.size()];
     
        
        for(int k =0;k<list.size();k++){
	    	  str = list.get(k).split("!");
	    	 
	    	  if (!(str[0].substring(0,5)).equals("Local"))  {
	    	
	    		  if(str[7].endsWith(Called_id)){
	    		 
	    			  channel_name=str[0];	
	    		 
	    	 } 
	      }
        }
        /*System.out.println(list.size()-3);
        str=new String[list.size()-3];
        str2=new String[list.size()-3];
        ArrayList<String> map = new ArrayList<>();
        int i = 0;
        while ( i <list.size()-3)
        {
        	str[i]=list.get(i);
	           // System.out.println(str[i]);
	            if (!(str[i].substring(0,5)).equals("Local"))	{
					
					map.add(str[i].toString());
					
				}
            i++;
        }
        System.out.println("data"+map.toString());
		//System.out.println(str[0]);
        i=0;
        do
        {
        	s1=map.get(0);
    		//s2=str[i];
    		int x=s1.indexOf("CallerID");
    		int y=s1.indexOf("Duration");
    		sub=map.get(i).substring(x,y-1).trim();
    		//System.out.println(sub);
    		str2[i]=sub;
    		i++;
        }while ( i <map.size());
		
	
	int j=0;
	
	while(j< str2.length){
	if(channel_name.equals(str2[j]))
	{
		int ext_place=j;
		s1=map.get(0);
		s2=map.get(ext_place);
		int x=s1.indexOf("Channel");
		int y=s1.indexOf("Context");
		sub=s2.substring(x,y-1).trim();
		//System.out.println(sub);
		channel_name=sub;
		
	}
	j++;
	}
*/
	
        action.setCommand("channel request hangup "+channel_name);
        response = (CommandResponse) c.sendAction(action);
        System.out.println(response.getResult());

        c.logoff();
    }

    public static void main(String[] args) throws Exception
    {
        new HangUp1().run(args[0]);
    }
}