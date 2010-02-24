#! /bin/bash

curl -u $(head -n 1 ~/.kgauth) -k -F file=@tiny.csv http://java-dev.freecode.no/krigsgraver/person/upload
