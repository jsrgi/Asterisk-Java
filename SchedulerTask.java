
import java.util.Timer;

/**
 *
 * @author Dhinakaran P.
 */

//Main class
public class SchedulerTask {
	public static void main(String args[]) throws InterruptedException {

		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
		time.schedule(st, 0, 1000); // Create Repetitively task for every 1 secs

		//for demo only.
		for (int i = 0; i <= 5; i++) {
			System.out.println("Execution in Main Thread...." + i);
			Thread.sleep(5000);
			if (i == 5) {
				System.out.println("Application Terminates");
				System.exit(0);
			}
		}
	}
}