

## RiTranslate: open-source translation for computational writing
<!--[![Build Status](https://travis-ci.org/dhowe/RiTranslate.svg?branch=master)](https://travis-ci.org/dhowe/RiTranslate)-->

<a href="http://www.gnu.org/licenses/gpl-3.0.en.html"><img src="https://img.shields.io/badge/license-GPL-orange.svg" alt="npm version"></a>

A set of new translation features for the RiTa toolkit, allowing users to  do programmatic, real-time translation from within RiTa-based works. 

<a href="https://rednoise.org/rita/"><img height=150 src="https://rednoise.org/ritran.png"/></a>

### With Processing

1. If needed, download and install [Processing](https://processing.org/download/?processing).
2. Download and unzip [RiTranslate.zip](https://github.com/dhowe/RiTranslate/raw/master/RiTranslate.zip) to the "libraries" folder inside your Processing sketchbook (detailed instructions [here](https://github.com/dhowe/RiTranslate/blob/master/install_instructions.txt)). 
3. Restart Processing, then create a test sketch as follows:
```processing
import rita.translate.*;

void setup() {

  GoogleTranslate googleTranslate = new GoogleTranslate();

  // language code reference: http://www.w3schools.com/tags/ref_language_codes.asp
  String translation = googleTranslate.translate("cat", "en", "zh-Hant");
  
  fill(0);
  textAlign(CENTER);
  text(translation, width/2, height/2);
}
```

### With Eclipse and Maven

1. Download and install [Eclipse](https://eclipse.org/downloads/)
2. Import RiTranslate, in Eclipse File > Import.. > Git > Projects from Git > Clone URI > URI : https://github.com/dhowe/RiTranslate.git 
3. Note: depending on your language, you may need to set your Eclipse editor to use the UTF8 character set: Window > Preferences > General > Workspace :: Text file encoding
4. To run the tests, navigate to src/test/java/GoogleTranslateTest.java in the package-explorer, then right-click and select  Run As > JUnit Test
 
<br>

---

Sponsored in part by a [Teaching Development Grant](http://www.cityu.edu.hk/edge/grant/tdg/) from the City University of Hong Kong.
