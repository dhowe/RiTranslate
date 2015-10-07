#!/bin/sh

set -e

mvn compile
#rm -rf dist/library dist/classes
#p -r target/classes* dist/
#d dist

cd target/classes
jar cf ../../plib/RiTranslate/library/ritranslate.jar *
cd -

jar tf plib/RiTranslate/library/ritranslate.jar

cd plib
jar cvf ../RiTranslate.zip *
cd ..

jar tf RiTranslate.zip
