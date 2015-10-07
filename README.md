

## RiTranslate: open-source translation for computational writing
<!--[![Build Status](https://travis-ci.org/dhowe/RiTranslate.svg?branch=master)](https://travis-ci.org/dhowe/RiTranslate)-->

<a href="http://www.gnu.org/licenses/gpl-3.0.en.html"><img src="https://img.shields.io/badge/license-GPL-orange.svg" alt="npm version"></a>

A set of new translation features for the RiTa toolkit, allowing users to  do programmatic, real-time translation from within their RiTa-based works. 

<a href="https://rednoise.org/rita/"><img height=180 src="https://rednoise.org/ritrans-rect.png"/></a>

### With Processing

1. Download and install [Processing](https://processing.org/download/?processing) (if needed).
2. Download and unzip [RiTranslate libraries](https://github.com/dhowe/RiTranslate/raw/master/RiTranslate.zip) to the "libraries" folder inside your Processing sketchbook (detailed instructions [here](https://github.com/dhowe/RiTranslate/blob/master/install_instructions.txt). 
3. After unzipping, your folder structure should be as follows:
```
Processing
  libraries
    RiTranslate
      library
        ritranslate.jar
        json-simple-XYZ.jar
        jsoup-XYZ.jar
```
4. Restart Processing, then create a simple test sketch as follows:
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
