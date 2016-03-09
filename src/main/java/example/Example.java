package example;

import translate.GoogleTranslate;

public class Example {

	public static void main(String[] args) {
		GoogleTranslate googleTranslate = new GoogleTranslate();
		
		// language code reference: http://www.w3schools.com/tags/ref_language_codes.asp
		String translation = googleTranslate.translate("cat", "en", "zh-Hant");
		System.out.println("Showing the best translation: ");
		System.out.println(translation);
		System.out.println();
		
		String[] translations = googleTranslate.translations("cat", "en", "zh-Hant");
		System.out.println("Showing all translations: ");
		for (int i = 0; translations != null && i < translations.length; i++) {
			System.out.println(translations[i]);
		}
		System.out.println();
		
		String[] translationsWithPOS = googleTranslate.translations("man", "en", "zh-Hant", "verb");
		System.out.println("Showing translations in verb: ");
		for (int i = 0; translationsWithPOS != null && i < translationsWithPOS.length; i++) {
			System.out.println(translationsWithPOS[i]);
		}
		System.out.println();
		
		String[] definitions = googleTranslate.definitions("cat", "noun");
		System.out.println("Showing definitions in noun: ");
		for (int i = 0; definitions != null && i < definitions.length; i++) {
			System.out.println(definitions[i]);
		}
		System.out.println();
		
		String[] exampleSentences = googleTranslate.examples("cat");
		System.out.println("Showing exampleSentences: ");
		for (int i = 0; exampleSentences != null && i < exampleSentences.length; i++) {
			System.out.println(exampleSentences[i]);
		}
		System.out.println();
		
		String[] glosses = googleTranslate.glosses("cat", "noun");
		System.out.println("Showing glosses: ");
		for (int i = 0; glosses != null && i < glosses.length; i++) {
			System.out.println(glosses[i]);
		}
		System.out.println();
		
		String[] seeAlso = googleTranslate.seeAlso("cat");
		System.out.println("Showing seeAlso: ");
		for (int i = 0; seeAlso != null && i < seeAlso.length; i++) {
			System.out.println(seeAlso[i]);
		}
		System.out.println();
		
		String[] synonyms = googleTranslate.synonyms("cat", "noun");
		System.out.println("Showing synonyms: ");
		for (int i = 0; synonyms != null && i < synonyms.length; i++) {
			System.out.println(synonyms[i]);
		}
		System.out.println();
		
		
	}
}