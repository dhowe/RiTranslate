#!/bin/sh

set -e

rm -rf dist/library dist/classes
cp -r target/classes dist/
cd dist
jar cvf ../plib/library/RiTranslate.jar classes/*
#ls -lR ../plib
cd ../plib
jar cvf ../RiTranslate.zip *
cd ..
jar tf RiTranslate.zip
