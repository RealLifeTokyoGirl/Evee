package linguistics;

/**
 * Contains definitions of morphological features such as tenses, person, pluralization, vowels, and consonants
**/

public class Morph {
	
	// Orthography

	public static final char[] VOWELS_ARRAY = {'a','e','i','o','u'};
	
	public static final String VOWELS_REGEX = "[aeiou]";
	
	public static final char[] CONSONANTS_ARRAY = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
	
	public static final String CONSONANTS_REGEX = "[bcdfghjklmnpqrstvwxyz]";
	
	
	// Tenses
	
	public static final int INFINITIVE = 0;
	
	public static final int PAST = 1;
	
	public static final int PRESENT_PARTICIPLE = 2;
	
	public static final int PAST_PARTICIPLE = 3;
	
	public static final int PRESENT = 4;
	
	
	// Person
	
	public static final int FIRST_PERSON = 1;
	
	public static final int SECOND_PERSON = 2;
	
	public static final int THIRD_PERSON = 3;
	
	
	// Pluralization
	
	public static boolean SINGULAR = false;
	
	public static boolean PLURAL = true;
	
	
	
	
	
	/** Verb conjugation tree **/
	
	/*
				if (tense == Morph.INFINITIVE) {
				
			}
			else if (tense == Morph.PAST) {
				
			}
			else if (tense == Morph.PRESENT_PARTICIPLE) {
				
			}
			else if (tense == Morph.PAST_PARTICIPLE) {
				
			}
			else if (tense == Morph.PRESENT) {
				if (person == Morph.FIRST_PERSON) {
					if (pluralization) {
						
					}
					
				}
				else if (person == Morph.SECOND_PERSON) {
					if (pluralization) {
						
					}
					
				}
				else if (person == Morph.THIRD_PERSON) {
					if (pluralization) {
						
					}
					
				}
			}
			else {
				return input;
			}
	*/
}
