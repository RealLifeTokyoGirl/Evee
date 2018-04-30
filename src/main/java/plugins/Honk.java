package plugins;

import org.pircbotx.Colors;

/**
 * Methods: 
 * honk(); [message.contains honk] - honks cutely
**/

public class Honk {
	
	private static int honks = 0;

	private static String[] colors = new String[] {Colors.BLACK, 
			Colors.CYAN,
			Colors.RED,
			Colors.MAGENTA,
			Colors.BLUE,
			Colors.YELLOW,
			Colors.OLIVE,
			Colors.PURPLE,
			Colors.TEAL,
			Colors.GREEN,
			Colors.DARK_BLUE};
	
	public static String honk() {
		
			String honk = colors[honks] + "honk~";
			honks++;
			if (honks == 11) {
				honks = 0;
			}
			return honk;
	}
	
}
