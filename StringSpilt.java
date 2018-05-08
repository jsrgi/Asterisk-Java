

public class StringSpilt {
public static void main(String ars[])
{
	String s="Description                              Alarms  IRQ    bpviol CRC    Fra Codi Options  LBO";
	String s2="Wildcard TE122 Card 0                    OK      1      -1     -1     CCS HDB3          0 db (CSU)/0-133 feet (DSX-1)";
			int x=s.indexOf("A");
			int y=s.indexOf("IRQ");
			System.out.println(s2.substring(x,y-1).trim());
}
}
