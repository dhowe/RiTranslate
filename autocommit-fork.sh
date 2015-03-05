#!/bin/sh

set -e

#git remote add upstream https://github.com/dhowe/RiTranslate

git config user.name "travis-auto"
git config user.email "travis-auto@outlook.com"

git fetch upstream
git checkout master
git merge upstream/master
git pull
TIMESTAMP=`date +%s`
sed -i "" "s/##[0-9][0-9]*##/##${TIMESTAMP}##/g" last_build
git status
git commit -a -m 'autocommit-ta'
git push   
git push -u upstream master
exit
