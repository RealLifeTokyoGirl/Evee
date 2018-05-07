//package plugins;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//
//import org.pircbotx.hooks.events.MessageEvent;
//
///**
// * Methods: 
// * score(MessageEvent event); [.score (name +|-number)] - fetches the current score, and allows the user to increment the values
//**/
//
//public class Score {
//	
//	
//	public static void score(MessageEvent event) {
//		
//		
//		
//		try {
//			File file = new File("src/main/resources/docs/score.txt");
//			Scanner	scan = new Scanner(file);
//			String[] names = scan.nextLine().split(", ");
//			scan.close();
//			
//			if (event.getMessage().matches(".score (\\w+ [-+]*\\d+|[-+]*\\d+ \\w+)")) {
//				String[] array = event.getMessage().toLowerCase().split(" ");
//				for (int i = 0; i < names.length; i++) {
//					if (names[i].toLowerCase().matches("#\\d{1,2} " + array[1] + " \\(\\d+\\)")) {
//						String score = names[i].substring(names[i].indexOf('(') + 1, names[i].indexOf(')'));
//						if (array[2].startsWith("+")) {
//							int newScore = Integer.parseInt(score) + Integer.parseInt(array[2].substring(1));
//							names[i] = names[i].substring(0, names[i].indexOf('(') + 1) + newScore + ")";
//						}
//						else if (array[2].startsWith("-")) {
//							int newScore = Integer.parseInt(score) - Integer.parseInt(array[2].substring(1));
//							names[i] = names[i].substring(0, names[i].indexOf('(') + 1) + newScore + ")";
//						}
//						else if (array[2].matches("\\d+")) {
//							int newScore = Integer.parseInt(score) + Integer.parseInt(array[2]);
//							names[i] = names[i].substring(0, names[i].indexOf('(') + 1) + newScore + ")";
//						}
//						break;
//					}
//					else if (names[i].toLowerCase().matches("#\\d{1,2} " + array[2] + " \\(\\d+\\)")) {
//						String score = names[i].substring(names[i].indexOf('(') + 1, names[i].indexOf(')'));
//						if (!array[1].startsWith("-")) {
//							int newScore = Integer.parseInt(score) + Integer.parseInt(array[1].substring(1));
//							names[i] = names[i].substring(0, names[i].indexOf('(') + 1) + newScore + ")";
//						}
//						else if (array[1].startsWith("-")) {
//							int newScore = Integer.parseInt(score) - Integer.parseInt(array[1].substring(1));
//							names[i] = names[i].substring(0, names[i].indexOf('(') + 1) + newScore + ")";
//						}
//						else if (array[1].matches("\\d+")) {
//							int newScore = Integer.parseInt(score) + Integer.parseInt(array[1]);
//							names[i] = names[i].substring(0, names[i].indexOf('(') + 1) + newScore + ")";
//						}
//						break;
//					}
//				}
//				String newScoreboard = "";
//				for (int i = 0; i < names.length; i++) {
//					newScoreboard = newScoreboard + names[i];
//					if (i + 1 < names.length) {
//						newScoreboard = newScoreboard + ", ";
//					}
//				}
//				
//				try {
//					FileWriter writer = new FileWriter(file);
//					BufferedWriter out = new BufferedWriter(writer);
//					out.write(newScoreboard);
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}				
//			}
//			
//			Scanner	scan2 = new Scanner(file);
//			event.respondChannel("The current scores are: " + scan2.nextLine());
//			scan2.close();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
