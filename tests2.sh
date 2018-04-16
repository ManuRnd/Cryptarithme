#!/bin/bash

MAIN=Solve
# MAIN=Cryptarithme

errors=0

for testFile in `ls tests2/*.in`
do
    out=${testFile%.in}.out
    got=`cat $testFile | java -cp bin $MAIN`
    expected=`cat $out`
    if [ "$got" = "$expected" ]
    then
        echo "$testFile: OK"
    else
        echo "$testFile: KO"
        errors=`expr $errors + 1`
    fi
done

echo "Error(s): $errors"
exit $errors
