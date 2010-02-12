#! /bin/bash

curl -u $(head -n 1 ~/.kgauth) -k -F file=@register10-01-18.csv http://java-dev.freecode.no/krigsgraver/person/upload
