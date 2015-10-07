#!/bin/sh

set -e

rm -f RiTranslate.zip

mvn compile

cd target/classes
jar cf ../../plib/RiTranslate/library/ritranslate.jar *
cd -

jar tf plib/RiTranslate/library/ritranslate.jar

cd plib
jar cf ../RiTranslate.zip *
cd ..

jar tf RiTranslate.zip
