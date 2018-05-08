import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.event.ManagerEvent;

public class HtmlEventTracer implements ManagerEventListener {
	public static void main(String[] args) throws Exception {
		  if (args.length != 3) {
		    System.err.println("Usage: java org.asteriskjava.tools.HtmlEventTracer host username password");
		    System.exit(1);
		  }
		  final HtmlEventTracer tracer;
		  final DefaultAsteriskServer server;
		  tracer=new HtmlEventTracer();
		  server=new DefaultAsteriskServer(args[0],args[1],args[2]);
		  server.initialize();
		  server.getManagerConnection().addEventListener(tracer);
		  System.err.println("Event tracer successfully started. Press Ctrl-C to write trace file and exit.");
		  Runtime.getRuntime().addShutdownHook(new Thread(){
		    @Override public void run(){
		      tracer.toString();//write();
		      server.shutdown();
		    }
		  }
		);
		  while (true) {
		    Thread.sleep(1000);
		  }
		}

	@Override
	public void onManagerEvent(ManagerEvent event) {
		System.out.println("heelo events"+event);
		
	}

}
