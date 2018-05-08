import java.util.ArrayList;
import java.util.List;
//import java.lang.*;
import java.util.*;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;



public class Manager1
{
    private ManagerConnection c;
Scanner s=new Scanner(System.in);

    public Manager1() throws Exception
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
                "localhost", "admin", "asterisk@321");
        c =  factory.createManagerConnection();
    }

    public void run() throws Exception
    {
        c.login();

        CommandAction action;
        CommandResponse response;
        List<String> list = new ArrayList<String>();
	String[] str;
	String[] str2;
	String s1,s2,sub;
      action = new CommandAction();
        action.setCommand("core show channels verbose");
	response = (CommandResponse) c.sendAction(action);

        list = response.getResult();
        System.out.println(list.size()-3);
        str=new String[list.size()-3];
        str2=new String[list.size()-3];
        int i = 0;
        while ( i <list.size()-3)
        {
		str[i]=list.get(i);
            //System.out.println(str[i]);
            i++;
        }
		//System.out.println(str[0]);
        i=0;
        do
        {
        	s1=str[0];
    		//s2=str[i];
    		int x=s1.indexOf("Extension");
    		int y=s1.indexOf("Prio");
    		sub=str[i].substring(x,y-1).trim();
    		//System.out.println(sub);
    		str2[i]=sub;
    		i++;
        }while ( i <list.size()-3);
		

        System.out.println("Enter extension to disconnect");
	String channel_name=s.next();
	
	int j=0;
	
	while(j< str2.length){
	if(channel_name.equals(str2[j]))
	{
		int ext_place=j;
		s1=str[0];
		s2=str[ext_place];
		int x=s1.indexOf("Channel");
		int y=s1.indexOf("Context");
		sub=s2.substring(x,y-1).trim();
		//System.out.println(sub);
		channel_name=sub;
		
	}
	j++;
	}

	
        action.setCommand("channel request hangup "+channel_name);
        response = (CommandResponse) c.sendAction(action);
	//list = response.getResult();
        //System.out.println(response.getResponse());
        //System.out.println(response.getResult());
              
        /*action.setCommand("core show channels");
	response = (CommandResponse) c.sendAction(action);

        list = response.getResult();
        System.out.println(list.size());
        i=0;
                while ( i <list.size())
        {

            System.out.println(list.get(i));
            i++;
        }*/

        c.logoff();
    }

    public static void main(String[] args) throws Exception
    {
        new Manager1().run();
    }
}