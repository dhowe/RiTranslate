#!/bin/sh

set -e

git pull
TIMESTAMP=`date +%s`
sed -i "" "s/##[0-9][0-9]*##/##${TIMESTAMP}##/g" last_build
git commit -a -m 'autocommit'
git push
