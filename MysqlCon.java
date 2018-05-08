
import java.sql.*;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;  
class MysqlCon{  
public static void main(String args[]){  
	ManagerConnectionFactory factory = new ManagerConnectionFactory(
			"localhost", "admin", "asterisk@321");
	// Retrieve the connection from the factory
	ManagerConnection managerConnection = factory.createManagerConnection();
	try {
		// login to Asterisk
		managerConnection.login();
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/asteriskcdrdb","root","asterisk@321");  
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from cdr");  
while(rs.next())  
	if (!rs.getString(12).equals("ANSWERED")){
System.out.println(rs.getString(6)+"    "+rs.getTimestamp(1));
	}
con.close();
managerConnection.logoff();
}catch(Exception e){ System.out.println(e);}  
}  
}  