#!/bin/sh

set -e

git remote add upstream https://github.com/dhowe/RiTranslate

git fetch upstream
git checkout master
git merge upstream/master
TIMESTAMP=`date +%s`
sed -i "" "s/##[0-9][0-9]*##/##${TIMESTAMP}##/g" last_build
git commit -a -m 'autocommit'
git push   
git push -u upstream master
