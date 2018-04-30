package linguistics;

import org.w3c.dom.*;
import xmlparser.ParseXML;

/**
 * Methods: 
 * getBase(int wordNumber); - fetches the base of word numbered wordNumber from the Nodelist object wordList
 * getPartOfSpeech(String inputWord); - gets part of speech of input word from the category tag, separated by space if multiple
 * getPartOfSpeech(int wordNumber); - fetches the part of speech of word numbered wordNumber from the Nodelist object wordList
 * getId(String inputWord); - gets id of input word from the id tag, separated by space if multiple
 * getWordNumber(String inputWord); - gets the index of the word tag in the NodeList object wordList *AS A STRING*, separated by space if multiple
**/

public class WordAttributes {
	
	public static String getBase(int wordNumber) {
		
		NodeList wordList = ParseXML.buildDoc("src/main/resources/docs/default-lexicon.xml").getElementsByTagName("word");
		String base = null;
		
		if (wordNumber >= wordList.getLength() || wordNumber < 0) {
			return null;
		}
		Node item = wordList.item(wordNumber);
		if (item.getNodeType() == Node.ELEMENT_NODE) {
			base = item.getFirstChild().getNextSibling().getTextContent();	
		}
		return base;
	}

	
	public static String getPartOfSpeech(String inputWord) {
		
		NodeList wordList = ParseXML.buildDoc("src/main/resources/docs/default-lexicon.xml").getElementsByTagName("word");
		String partOfSpeech = null;
		
		for (int i = 0; i < wordList.getLength(); i++) { // Iterate through each word tag
			Node current = wordList.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element word = (Element) current;
				NodeList wordAttributes = word.getChildNodes(); // Get the children of current word node
				for (int j = 0; j < wordAttributes.getLength(); j++) { // Iterate through child nodes
					Node currentAttribute = wordAttributes.item(j);
					if (currentAttribute.getNodeType() == Node.ELEMENT_NODE) {
						Element attribute = (Element) currentAttribute;
						if (attribute.getTagName() == "base" && attribute.getTextContent().equals(inputWord)) { // Check if base tag matches inputWord
							Element category = (Element) attribute.getNextSibling().getNextSibling(); // Sets partOfSpeech equal to the category tag
							if (partOfSpeech != null) {
								partOfSpeech = partOfSpeech + " " + category.getTextContent();
							}
							else {
								partOfSpeech = category.getTextContent();
							}
						}
					}
				}
			}
		}
		return partOfSpeech;
	}
	
	
	public static String getPartOfSpeech(int wordNumber) {
		
		NodeList wordList = ParseXML.buildDoc("src/main/resources/docs/default-lexicon.xml").getElementsByTagName("word");
		String partOfSpeech = null;
		
		if (wordNumber >= wordList.getLength() || wordNumber < 0) {
			return null;
		}
		Node item = wordList.item(wordNumber);
		if (item.getNodeType() == Node.ELEMENT_NODE) {
			Node base = item.getFirstChild().getNextSibling();
			partOfSpeech = base.getNextSibling().getNextSibling().getTextContent();
		}
		return partOfSpeech;
	}
	

	public static String getId(String inputWord) {
			
		NodeList wordList = ParseXML.buildDoc("src/main/resources/docs/default-lexicon.xml").getElementsByTagName("word");
		String id = null;
		
		for (int i = 0; i < wordList.getLength(); i++) { // Iterate through each word tag
			Node current = wordList.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element word = (Element) current;
				NodeList wordAttributes = word.getChildNodes(); // Get the children of current word node
				for (int j = 0; j < wordAttributes.getLength(); j++) { // Iterate through child nodes
					Node currentAttribute = wordAttributes.item(j);
					if (currentAttribute.getNodeType() == Node.ELEMENT_NODE) {
						Element attribute = (Element) currentAttribute;
						if (attribute.getTagName() == "base" && attribute.getTextContent().equals(inputWord)) { // Check if base tag matches inputWord
							Element ID = (Element) attribute.getNextSibling().getNextSibling().getNextSibling().getNextSibling(); // Sets id equal to the ID tag
							if (id != null) {
								id = id + " " + ID.getTextContent();
							}
							else {
								id = ID.getTextContent();
							}
						}
					}
				}
			}
		}
		return id;
	}
	
	
	public static String getWordNumber(String inputWord) {
		
		NodeList wordList = ParseXML.buildDoc("src/main/resources/docs/default-lexicon.xml").getElementsByTagName("word");
		String wordNumber = null;
		
		for (int i = 0; i < wordList.getLength(); i++) { // Iterate through each word tag
			Node current = wordList.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element word = (Element) current;
				NodeList wordAttributes = word.getChildNodes(); // Get the children of current word node
				for (int j = 0; j < wordAttributes.getLength(); j++) { // Iterate through child nodes
					Node currentAttribute = wordAttributes.item(j);
					if (currentAttribute.getNodeType() == Node.ELEMENT_NODE) {
						Element attribute = (Element) currentAttribute;
						if (attribute.getTagName() == "base" && attribute.getTextContent().equals(inputWord)) { // Check if base tag matches inputWord
							if (wordNumber != null) {
								wordNumber = wordNumber + " " + i;
							}
							else {
								wordNumber = Integer.toString(i);
							}
						}
					}
				}
			}
		}
		return wordNumber;
	}

}
