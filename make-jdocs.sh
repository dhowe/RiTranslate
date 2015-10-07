#!/bin/sh

set -e

rm -rf docs
mvn javadoc:javadoc
cp -r target/site/apidocs docs
