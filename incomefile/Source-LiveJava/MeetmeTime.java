import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.CommandResponse;

public class MeetmeTime {

	private BufferedWriter out = null;

	public MeetmeTime(ManagerConnection c) {

		try {
//c.login();

			out = new BufferedWriter(new FileWriter("MeetmeTime.txt", false));

			CommandAction action;
			CommandResponse response;
			List<String> list = new ArrayList<String>();
			String[] str;
			action = new CommandAction();
			action.setCommand("meetme list");
			response = (CommandResponse) c.sendAction(action);

			list = response.getResult();

			// System.out.println(list.size());
			// System.out.println(list.toString());
			str = new String[list.size()];
			// str2=new String[list.size()];
			int k = 1;
			while (k < list.size() - 1) {
				
				str[k] = list.get(k);

				out.write(str[k]);

				out.newLine();

				k++;
			}
			out.flush();
			out.close();
//c.logoff();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
