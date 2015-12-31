#!/bin/sh
set -e
mvn compile
mvn exec:java -Dexec.mainClass="rita.translate.GoogleTranslate"
