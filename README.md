

## RiTranslate: open-source translation for computational writing
<!--[![Build Status](https://travis-ci.org/dhowe/RiTranslate.svg?branch=master)](https://travis-ci.org/dhowe/RiTranslate)-->

<a href="http://www.gnu.org/licenses/gpl-3.0.en.html"><img src="https://img.shields.io/badge/license-GPL-orange.svg" alt="npm version"></a>

A set of new translation features for the RiTa toolkit, allowing users to  do programmatic, real-time translation from within their RiTa-based works. 

<a href="https://rednoise.org/rita/"><img height=180 src="https://rednoise.org/ritrans-rect.png"/></a>

### With Processing

1. Download and install [Processing](https://processing.org/download/?processing) (preferably version 3.0 or newer)
2. Download and extract [RiTranslate libraries](https://github.com/dhowe/RiTranslate/raw/master/RiTranslate.zip) to the library folder under Processing Sketch or refer to the detailed instructions on [installing Processing libraries](https://github.com/dhowe/RiTranslate/blob/master/install_instructions.txt). Restart Processing after library installation.
3. Recommended to enable the Code Completion feature in Processing 3.0 by checking Processing > Preferences > Code completion with Ctrl-space option

Create a simple test sketch as follows
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
3. Set Eclipse editor to UTF-8 in Window > Preferences > General > Workspace : Text file encoding
4. Run JUnit test from Package Explorer > RiTranslate > src/test/java > (default package) > Right Click on GoogleTranslateTest.java > Run As > JUnit Test


<br>

 
<br>
 
<br>
 
<br>

---

Sponsored in part by a Teaching Development Grant from the City University of Hong Kong
