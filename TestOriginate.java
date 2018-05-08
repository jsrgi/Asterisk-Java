
import java.io.IOException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
 import org.asteriskjava.manager.ManagerConnectionFactory;
 import org.asteriskjava.manager.TimeoutException;
 //import org.asteriskjava.manager.action.HangupAction;
 //import org.asteriskjava.manager.action.OriginateAction;
 import org.asteriskjava.manager.action.RedirectAction;
 import org.asteriskjava.manager.response.ManagerResponse;

 public class TestOriginate {

     /**
      * @param args
      */
     private ManagerConnection managerConnection;

     public TestOriginate() throws IOException {
         ManagerConnectionFactory factory = new ManagerConnectionFactory(
                 "localhost", "admin", "asterisk@321");

         this.managerConnection = factory.createManagerConnection();

     }
     public void run() {
         RedirectAction redirectAction;
         ManagerResponse originateResponse;
         String state = "";
                  //String receiver = "5221";
         redirectAction = new RedirectAction();
         redirectAction.setContext("from-internal");
         redirectAction.setExten("1234");
         redirectAction.setPriority(new Integer(1));
         redirectAction.setChannel("SIP/5221");

         try {
             System.out.println("Starting login localhost");
             managerConnection.login();

             System.out.println("After login localhost");

         } catch (IllegalStateException e) {

         } catch (TimeoutException e) {

         } catch (IOException e) {

         } catch (AuthenticationFailedException e) {

         }
         try {
             originateResponse =
 managerConnection.sendAction(redirectAction,
                     30000);
             state = originateResponse.getResponse();
             System.out.println("State value is :" + state);
         } catch (IllegalArgumentException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IllegalStateException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (TimeoutException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

         managerConnection.logoff();
     }

     public static void main(String[] args) throws IOException {
         // TODO Auto-generated method stub

        TestOriginate test = new TestOriginate();
         test.run();
    }

 }