#!/bin/sh

set -e

mvn javadoc:javadoc
cp -r target/site/apidocs docs
