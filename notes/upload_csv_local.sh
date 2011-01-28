#! /bin/bash

if [ "$1" == "" ]; then
  echo "Missing file parameter (e.g. 'blabla.csv')"
  exit
fi

curl -u $(head -n 1 ~/.kgauth) -k -F file=@$1 http://localhost:8080/krigsgraver/person/upload
