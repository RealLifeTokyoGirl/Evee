package linguistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import xmlparser.ParseXML;

/**
 * Methods: 
 * conjugateIrregularVerbs(String input, TENSE, PERSON, PLURALIZATION); - conjugates irregular verbs using the list at docs/irregular-verbs.txt
 * conjugateVerbs(String input, TENSE, PERSON, PLURALIZATION); - conjugates regular verbs
 * findVerb(String input); - finds and returns the first verb in a given sentence
 * findNthVerb(String input, int n); - finds and returns the nth verb in a given sentence. If none exists call findVerb
**/

public class Verbs {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < ParseXML.buildDoc("src/main/resources/docs/default-lexicon.xml").getElementsByTagName("word").getLength(); i++) {
			String base = WordAttributes.getBase(i);
			if (WordAttributes.getPartOfSpeech(i).equals("verb")) {
				System.out.println(base + ": " + 
						conjugateVerbs(base, Morph.PAST, Morph.THIRD_PERSON, Morph.SINGULAR) + ", " +
						conjugateVerbs(base, Morph.PRESENT_PARTICIPLE, Morph.THIRD_PERSON, Morph.SINGULAR) + ", " +
						conjugateVerbs(base, Morph.PAST_PARTICIPLE, Morph.THIRD_PERSON, Morph.SINGULAR) + ", " +
						conjugateVerbs(base, Morph.PRESENT, Morph.THIRD_PERSON, Morph.SINGULAR));
			}
		}
		
	}
	
	private static String conjugateIrregularVerbs(String input, int tense, int person, boolean pluralization) {
		
		String[] array = input.split("(: |, )");
		
		if (tense == Morph.INFINITIVE) {
			return array[0];
		}
		else if (tense == Morph.PAST) {
			if (array[0].equals("be")) {
				if (pluralization || (!pluralization && person == Morph.SECOND_PERSON)) {
					return "were";
				}
				return "was";
			}
			return array[1];
		}
		else if (tense == Morph.PRESENT_PARTICIPLE) {
			return array[2];
		}
		else if (tense == Morph.PAST_PARTICIPLE) {
			return array[3];
		}
		else if (tense == Morph.PRESENT) {
			if (person == Morph.FIRST_PERSON) {
				if (pluralization) {
					if (array[0].equals("be")) {
						return "are";
					}
					return array[0];
				}
				if (array[0].equals("be")) {
					return "am";
				}
				return array[0];
			}
			else if (person == Morph.SECOND_PERSON) {
				if (pluralization) {
					if (array[0].equals("be")) {
						return "are";
					}
					return array[0];
				}
				if (array[0].equals("be")) {
					return "are";
				}
				return array[0];
			}
			else if (person == Morph.THIRD_PERSON) {
				if (pluralization) {
					if (array[0].equals("be")) {
						return "are";
					}
					return array[0];
				}
				if (array[0].equals("be")) {
					return "is";
				}
				else if (array[0].equals("have")) {
					return "has";
				}
				else if (array[0].matches("\\w*(o|ch)")) {
					return array[0] + "es";
				}
				else if (array[0].matches("\\w*y")) {
					return array[0].substring(0, array[0].length() - 1) + "ies";
				}
				return array[0] + "s";
			}
		}
		
		return "ERROR/NULL";
	}
	
	public static String conjugateVerbs(String input, int tense, int person, boolean pluralization) {
		
		try {
			Scanner	scan = new Scanner(new File("src/main/resources/docs/irregular-verbs.txt"));
			
			while (scan.hasNextLine()) {
				String thisLine = scan.nextLine();
				if (thisLine.startsWith(input + ":")) {
					scan.close();
					return conjugateIrregularVerbs(thisLine, tense, person, pluralization);
				}
			}
			scan.close();
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		
		if (input.matches("\\w*" + Morph.CONSONANTS_REGEX + "y")) {		// Ends in consonant + y
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input.substring(0, input.length() - 1) + "ied";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input.substring(0, input.length() - 1) + "ied";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input.substring(0, input.length() - 1) + "ies";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*(s|z|ch|sh|x)")) {		// Ends in silibant
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "es";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*" + "e")) {		// Ends in e
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + "d";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				if (input.matches("\\w*" + "ee")) {
					return input + "ing";
				}
				else if (input.matches("\\w*" + "ie")) {
					return input.substring(0, input.length() - 2) + "ying";
				}
				return input.substring(0, input.length() - 1) + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + "d";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "s";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*((" + Morph.VOWELS_REGEX + "{2})|(" + Morph.VOWELS_REGEX + "{1}y)|(" + Morph.VOWELS_REGEX + "w))" + Morph.CONSONANTS_REGEX + "*")) {	// Ends in diphthong + 0 or more consonants
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "s";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*" + Morph.VOWELS_REGEX + "+" + Morph.CONSONANTS_REGEX + "+" + Morph.VOWELS_REGEX + "{1}" + Morph.CONSONANTS_REGEX + "{1}")) {	// Ends in short vowel + consonant, 2+ syllables 
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "s";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*" + Morph.CONSONANTS_REGEX + "+" + Morph.VOWELS_REGEX + "{1}" + Morph.CONSONANTS_REGEX + "{1}")) {		// Ends in short vowel + consonant
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + input.substring(input.length() - 1) + "ed";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + input.substring(input.length() - 1) + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + input.substring(input.length() - 1) + "ed";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "s";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*" + Morph.CONSONANTS_REGEX + "{2,}")){		// Ends in consonant cluster
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "s";
				}
			}
			else {
				return input;
			}
		}
		else if (input.matches("\\w*o")) {		// Ends in o
			
			if (tense == Morph.INFINITIVE) {
				return input;
			}
			else if (tense == Morph.PAST) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				return input + "ing";
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				return input + "ed";
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						return input;
					}
					return input;
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						return input;
					}
					return input + "es";
				}
			}
			else {
				return input;
			}
		}
		else {
			System.out.println(input + ": ERROR/NULL");
		}
		return input; 
	}
	
	
	public static String findVerb(String input) {
		
		input = input.replaceAll("[^A-Za-z0-9 ]", "");
		String[] array = input.toLowerCase().split(" ");
		String[] partsOfSpeech = new String[array.length];
		
		for (int i = 0; i < partsOfSpeech.length; i++) {
			partsOfSpeech[i] = WordAttributes.getPartOfSpeech(array[i]);
		}
		for (int i = 0; i < partsOfSpeech.length; i++) {
			if (partsOfSpeech[i].contains("verb")) {
				return array[i];
			}
		}
		return null;
	}
	
	
	public static String findNthVerb(String input, int n) {
		
		input = input.replaceAll("[^A-Za-z0-9 ]", "");
		String[] array = input.toLowerCase().split(" ");
		String[] partsOfSpeech = new String[array.length];
		int verbs = 0;
		
		for (int i = 0; i < partsOfSpeech.length; i++) {
			partsOfSpeech[i] = WordAttributes.getPartOfSpeech(array[i]);
		}
		for (int i = 0; i < partsOfSpeech.length; i++) {
			if (partsOfSpeech[i].contains("verb")) {
				verbs++;
				if (verbs == n) {
					return array[i];
				}
			}
		}
		return findVerb(input);
	}

}
