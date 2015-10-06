

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import translate.GoogleTranslate;

public class GoogleTranslateTest {

	private GoogleTranslate googleTranslate;

	// has only "see also" section
	private String testString_01 = "meant";
	
	// following keywords should have no returns or anything
	private String testString_02 = "ASDF";
	private String testString_03 = "JSON";
	
	@Before
	public void setUp() {
		googleTranslate = new GoogleTranslate();
	}
	
	@Before
	public void initialise() throws InterruptedException {
	    Thread.sleep(2000);
	}

	@After
	public void tearDown() {
		googleTranslate = null;
	}

	@Test
	public void testTranslationsStringStringStringString() {
		String [] expectedResultFrom_dog_verb = {
				"suivre de près",
				"suivre comme un chien"
		};
		
		assertArrayEquals( expectedResultFrom_dog_verb, googleTranslate.translations("dog", "en", "fr", "verb") );
	
		String [] expectedResultFrom_dog_noun = {
				"chien",
				"mâle",
				"fille moche"
		};
		
		assertArrayEquals( expectedResultFrom_dog_noun, googleTranslate.translations("dog", "en", "fr", "noun") );
	
	
		assertNull( googleTranslate.translations("asdfasdf", "en", "fr", "noun") );
		
		
	}

	@Test
	public void testExamples() {
		// return value of examples() is dynamic 
		// that no hard-coded expected results can be done

		assertNotNull( googleTranslate.examples("apple") );
		
		assertNull( googleTranslate.examples( testString_01 ) );
		assertNull( googleTranslate.examples( testString_02 ) );
		assertNull( googleTranslate.examples( testString_03 ) );
	}

	@Test
	public void testGlosses() {
		
		String [] expectedResult = {
				"a child at play may use a stick as an airplane",
				"rain interrupted the second day's play",
				"the actors put on a new play",
				"the steering rack was loose, and there was a little play"
		};
		
		assertArrayEquals( expectedResult, googleTranslate.glosses("play", "noun") );
		
		String [] expectedResult02 = {
				"looking manly and capable in his tennis whites"
		};
		
		assertArrayEquals( expectedResult02, googleTranslate.glosses("manly", "adjective") );
		
		assertNull( googleTranslate.glosses( testString_01, "verb" ) );
		assertNull( googleTranslate.glosses( testString_02, "" ) );
		assertNull( googleTranslate.glosses( testString_03, "adjective" ) );
	}

	@Test
	public void testSeeAlso() {
		
		String [] expectedResultFrom_dog = {
				"hot dog",
				"dog leash",
				"dog food",
				"dog tag",
				"big dog",
				"female dog",
				"little dog",
				"dog bone",
				"stray dog",
				"dog and cat"
		};
		
		assertArrayEquals( expectedResultFrom_dog, googleTranslate.seeAlso("dog") );
		
		String [] expectedResultFrom_exert = {
				"exert oneself",
				"exert effort",
				"exert influence"
		};
		
		assertArrayEquals( expectedResultFrom_exert, googleTranslate.seeAlso("exert") );
		
		assertNull( googleTranslate.seeAlso( testString_02 ) );
		assertNull( googleTranslate.seeAlso( testString_03 ) );
	}

	@Test
	public void testDefinitions() {
		
		String [] expectedResult = {
				"play games of chance for money.",
				"manipulate (a situation), typically in a way that is unfair or unscrupulous."
		};

		assertArrayEquals( expectedResult, googleTranslate.definitions("game", "verb") );
		
		String testString02 = "manly";
		
		String [] expectedResult02 = {
				"having or denoting those good qualities traditionally associated with men, such as courage and strength."
		};	
		assertArrayEquals( expectedResult02, googleTranslate.definitions( testString02, "adjective" ) );
		
		assertNull( googleTranslate.definitions( testString02, "noun" ) );

		assertNull( googleTranslate.definitions( testString_01, "noun" ) );
		assertNull( googleTranslate.definitions( testString_02, "adjective" ) );
		assertNull( googleTranslate.definitions( testString_03, "adverb" ) );
	}

	@Test
	public void testSynonyms() {

		String testString02 = "manly";
		
		String [] expectedResult02 = {
				"manfully"
		};	
		assertArrayEquals( expectedResult02, googleTranslate.synonyms( testString02, "adverb" ) );
		
		assertNull( googleTranslate.synonyms( testString02, "noun" ) );
		
		assertNull( googleTranslate.synonyms( testString_01, "" ) );
		assertNull( googleTranslate.synonyms( testString_01, "verb" ) );
		assertNull( googleTranslate.synonyms( testString_01, "noun" ) );
		assertNull( googleTranslate.synonyms( testString_01, "adjective" ) );
		
	}

	@Test
	public void testTranslationsStringStringString() {
		
		String [] expectedResultFrom_dog_verb = {
				"chien",
				"mâle",
				"fille moche",
				"suivre de près",
				"suivre comme un chien"
		};
		
		assertArrayEquals( expectedResultFrom_dog_verb, googleTranslate.translations("dog", "en", "fr") );
	
		String [] expectedResultFrom_gamely = {
				"courageusement",
				"résolument",
				"avec bonne volonté"
		};
		assertArrayEquals( expectedResultFrom_gamely, googleTranslate.translations("gamely", "en", "fr") );

		assertNull( googleTranslate.translations("asdfasdf", "en", "fr") );
		assertNull( googleTranslate.translations("tbmrdvjj", "en", "fr") );
		
	}

	@Test
	public void testTranslate() {

		assertEquals( "apporter", googleTranslate.translate("bring", "en", "fr") );
		assertEquals( "comida", googleTranslate.translate("food", "en", "es") );
		assertEquals( "man", googleTranslate.translate("man", "es", "en") );
	}

}
