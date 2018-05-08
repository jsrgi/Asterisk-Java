
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class DemoServerSnoop extends GenericServlet{
     
    public void service(ServletRequest req , ServletResponse res)
            throws ServletException,IOException{
         
        res.setContentType("text/plain");
        PrintWriter out= res.getWriter();
        out.println("req.getServerName()" + req.getServerName());
        out.println("req.getServerPort()" + req.getServerPort());
         
        out.println("ServletContext().getServerInfo()" + 
                getServletContext().getServerInfo());
         
        out.println("getServerInfo() name:" + 
                getServerInfoName(getServletContext().getServerInfo()));
         
        out.println("getServerInfo() version:" + 
                getServerInfoVersion(getServletContext().getServerInfo()));
         
        out.println("getServerContext().getAttribute(\"attribute\")" + 
                getServletContext().getAttribute("attribute"));
    }
    private String getServerInfoName(String serverInfo){
         
        int slash = serverInfo.indexOf('/');
        if(slash==-1)
            return serverInfo;
        else
            return (String) serverInfo.subSequence(0,slash);
    }
    private String getServerInfoVersion(String serverInfo){
         
        int slash = serverInfo.indexOf('/');
        if(slash==-1)
            return null;
        else
            return serverInfo.substring(slash + 1);
    }}