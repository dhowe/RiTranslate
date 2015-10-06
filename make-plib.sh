#!/bin/sh

set -e

rm -rf dist/library dist/classes
cp -r target/classes dist/
cd dist
jar cvf ../plib/RiTranslate/library/ritranslate.jar classes/*
cd ../plib
jar cvf ../RiTranslate.zip *
cd ..
jar tf RiTranslate.zip
