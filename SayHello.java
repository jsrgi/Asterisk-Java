import java.util.Timer;
//import java.util.Timer.*;
import java.util.TimerTask;

class SayHello extends TimerTask {
    public void run() {
       System.out.println("Hello World!"); 
    }
    public static void main(String args[])
    {
    	Timer timer = new Timer();
    	timer.schedule(new SayHello(), 0, 1000);
    }
}

// And From your main() method or any other method
