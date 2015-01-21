package google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import rita.RiScraper;
import rita.RiTa;

public class GoogleTranslate {
	static String TEST_JSON_DATA = null; // set to null to use real data from google

	public static String URL = "https://translate.google.com/translate_a/single?client=t&sl=%SL%&tl=%TL%&hl=en&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&otf=2&ssel=0&tsel=0&tk=516155|367641&q=%TEXT%";

	/**
	 * Returns all translations for the text in ranked order for the given
	 * part-of-speech
	 */
	public String[] translations(String text, String fromLang, String toLang,
			String pos) {

		String call = URL.replace("%SL%", fromLang).replace("%TL%", toLang)
				.replace("%TEXT%", text);
		JSONArray array = resultArray(call);

		if (array.size() <= 5)
			return null;

		JSONArray trans = (JSONArray) array.get(1);

		if (pos == null) {

			// return translations in ranked order
			List<String> list_string = new ArrayList<String>();
			for (int i = 0; i < trans.size(); i++) {

				JSONArray buffer = (JSONArray) ((JSONArray) trans.get(i))
						.get(1);

				for (int j = 0; j < buffer.size(); j++) {
					list_string.add((String) buffer.get(j));
				}
			}
			String[] s = new String[list_string.size()];
			list_string.toArray(s);

			// for (int j = 0; j < s.length; j++)
				// System.out.println(s[j]);

			return s;

		} else {
			String p = pos.toLowerCase();
			// String[] s = new String[trans.size()];
			for (int i = 0; i < trans.size(); i++) {

				String temp = (String) ((ArrayList) trans.get(i)).get(0);

				if (p.equals(temp)) {

					JSONArray buffer = (JSONArray) ((JSONArray) trans.get(i))
							.get(1);
					List<String> list_string = new ArrayList<String>();

					for (int j = 0; j < buffer.size(); j++) {
						list_string.add((String) buffer.get(j));
					}
					String[] s = new String[list_string.size()];
					list_string.toArray(s);

					// for (int j = 0; j < s.length; j++)
						// System.out.println(s[j]);

					return s;
				}
			}
		}
		return null;
	}

	/**
	 * Returns all examples for the text
	 */
	@SuppressWarnings("rawtypes")
	public String[] examples(String text) {
		
		String call = URL.replace("%SL%", "en").replace("%TL%", "en")
				.replace("%TEXT%", text);
		JSONArray array = resultArray(call);

		// System.out.println( "array: " + array );
		// System.out.println( "array.size(): " + array.size() );
		
		if ( array.size() < 6 ) {
			return null;
		}
		
		JSONArray trans = (JSONArray) ((JSONArray) array.get(array.size() - 2)).get(0);
		// System.out.println( "trans: " + trans );

		String[] s = new String[trans.size()];
		if ( trans.size() == 1 ) {
			// System.out.println( "trans.size(): " + trans.size() );
			s[0] = text;
			return s;
		}
		for (int i = 0; i < trans.size(); i++) {
			s[i] = (String) ((ArrayList) trans.get(i)).get(0);
			s[i] = s[i].replace("<b>", "").replace("</b>", "");
			// System.out.println(s[i]);
		}
		return s;
	}

	/**
	 * Returns all glosses for the text/pos
	 */
	@SuppressWarnings("rawtypes")
	public String[] glosses(String word, String pos) {

		String call = URL.replace("%SL%", "en").replace("%TL%", "en")
				.replace("%TEXT%", word);
		JSONArray array = resultArray(call);
		
		// System.out.println( "array: " + array );
		// System.out.println( "array.size(): " + array.size() );
		
		if ( array.size() < 6) {
			return null;
		}
		
		JSONArray trans = ( (JSONArray) array.get( 5 ) );
		// System.out.println(trans);
		String p = pos.toLowerCase();
						
		for (int i = 0; i < trans.size(); i++) {
			String temp = (String) ((ArrayList) trans.get(i)).get(0);
			
			if ( p.equals( temp ) ) {
				// System.out.println("i: " + i);
				
				JSONArray buffer = (JSONArray) ((JSONArray) trans.get(i)).get(1);
				List<String> list_string = new ArrayList<String>();
				
				for (int j = 0; j < buffer.size(); j++) {
					// System.out.println( ((ArrayList) buffer.get(j)).get(0) );
					if ( ((ArrayList) buffer.get(j)).size() > 2) {
						list_string.add( (String) ((ArrayList) buffer.get(j)).get(2) );
						// System.out.println( (String) ((ArrayList) buffer.get(j)).get(2) );
					}
				}
				String[] s = new String[ list_string.size() ];
				list_string.toArray( s );

				// for (int j = 0; j < s.length; j++)
					// System.out.println(s[j]);
				
				return s;
			}
		}
		
		return null;
	}

	/**
	 * Returns all see also for the text
	 */
	public String[] seeAlso(String text) {

		String call = URL.replace("%SL%", "en").replace("%TL%", "en")
				.replace("%TEXT%", text);
		JSONArray array = resultArray(call);
		
		// System.out.println( "array: " + array );
		// System.out.println( "array.size(): " + array.size() );
		
		if ( array.size() < 5) {
			return null;
		}
		
		JSONArray trans = (JSONArray) ((JSONArray) array.get(array.size() - 1))
				.get(0);

		String[] s = new String[trans.size()];
		for (int i = 0; i < trans.size(); i++) {
			s[i] = (String) trans.get(i);
			// System.out.println(s[i]);
		}

		return s;
	}
	
	/**
	 * Returns all definitions for the text/pos
	 */
	@SuppressWarnings("rawtypes")
	public String[] definitions(String word, String pos) {

		String call = URL.replace("%SL%", "en").replace("%TL%", "en")
				.replace("%TEXT%", word);
		JSONArray array = resultArray(call);
		
		// System.out.println( "array: " + array );
		// System.out.println( "array.size(): " + array.size() );
				
		if ( array.size() < 6 ) {
			return null;
		}
		
		JSONArray trans = ((JSONArray) array.get( 5 ));
		// System.out.println( "trans: " + trans );

		String p = pos.toLowerCase();
		String[] s;
						
		for (int i = 0; i < trans.size(); i++) {
			String temp = (String) ((ArrayList) trans.get(i)).get(0);
			
			if ( p.equals( temp ) ) {
				// System.out.println("i: " + i);
				
				JSONArray buffer = (JSONArray) ((JSONArray) trans.get(i)).get(1);
				s = new String[buffer.size()];
				
				for (int j = 0; j < buffer.size(); j++) {
					// System.out.println( ((ArrayList) buffer.get(j)).get(0) );
					s[j] = (String) ((ArrayList) buffer.get(j)).get(0);
					// System.out.println(s[j]);
				}
				return s;
			}
		}
		
		return null;
	}

	/**
	 * Returns all synonyms for the text/pos
	 */
	@SuppressWarnings("rawtypes")
	public String[] synonyms(String word, String pos) {
		
		String call = URL.replace("%SL%", "en").replace("%TL%", "en")
				.replace("%TEXT%", word);
		JSONArray array = resultArray(call);
		
		// System.out.println( "array: " + array );
		// System.out.println( "array.size(): " + array.size() );
				
		if ( array.size() < 6 ) {
			return null;
		}

		JSONArray trans = (JSONArray) array.get( 4 );
		// System.out.println( "trans: " + trans );
		
		Object cmp = new Double(1.1);
		// check if this is Double type, if yes then that means no synonyms
		if ( ((ArrayList) trans.get(1)).get(0).getClass().equals( cmp.getClass() ) )
			return null;
		
		String p = pos.toLowerCase();
						
		for (int i = 0; i < trans.size(); i++) {
			String temp = (String) ((ArrayList) trans.get(i)).get(0);

			if (p.equals(temp)) {

				JSONArray buffer = (JSONArray) ((JSONArray) trans.get(i))
						.get(1);
				List<String> list_string = new ArrayList<String>();

				for (int j = 0; j < buffer.size(); j++) {
					JSONArray jsonarray_j = (JSONArray) buffer.get(j);
					JSONArray jsonarray_k = (JSONArray) jsonarray_j.get(0);

					for (int k = 0; k < jsonarray_k.size(); k++) {
						// System.out.println( jsonarray_k.get(k) );
						list_string.add((String) jsonarray_k.get(k));
					}
				}
				// String[] s = new String[ list_string.size() ];
				// list_string.toArray( s );
			    // List<String> list = Arrays.asList(s);
				
				// remove duplicates
			    Set<String> set = new HashSet<String>(list_string);
			    String[] s = new String[set.size()];
			    set.toArray(s);
			    
				// for (int j = 0; j < s.length; j++)
					// System.out.println(s[j]);
				
				return s;
			}
		}

		return null;
	}

	// -------------------------------------------------------------------------

	/**
	 * Returns all translations for the text in ranked order by part-of-speech
	 */
	public String[] translations(String text, String fromLang, String toLang) {
		return translations(text, fromLang, toLang, null);
	}

	/**
	 * Returns the best translation for the text
	 */
	public String translate(String text, String fromLang, String toLang) {

		String call = URL.replace("%SL%", fromLang).replace("%TL%", toLang)
				.replace("%TEXT%", text);
		JSONArray array = resultArray(call);

		return array == null ? null : (((JSONArray) ((JSONArray) array.get(0))
				.get(0)).get(0)).toString();
	}

	private static JSONArray resultArray(String urlTocall) {
		RiScraper scraper = new RiScraper();
		scraper.ignoreContentType(true);

		String json = TEST_JSON_DATA != null ? RiTa.loadString(TEST_JSON_DATA)
				: scraper.connect(urlTocall).body();

		Object obj = JSONValue.parse(json);
		JSONArray array = (JSONArray) obj;

		// for (int i = 0; i < array.size(); i++)
			// System.out.println(i+") "+array.get(i));

		return array;
	}

	public static void main(String[] args) {

		// new GoogleTranslate().translations("gamely", "en", "fr");
		// System.out.println(new GoogleTranslate().translate("bring", "en", "zh-cn"));
		
		// new GoogleTranslate().seeAlso("dog");
		// new GoogleTranslate().examples("meant");
		// new GoogleTranslate().definitions("manly", "adjective");
		// new GoogleTranslate().synonyms("good", "noun");
		// new GoogleTranslate().synonyms("manly", "adjective");
		// new GoogleTranslate().glosses("meant", "verb");

	}
}
