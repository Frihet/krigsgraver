#! /bin/bash

curl http://java-dev.freecode.no/krigsgraver/admin/insertUser
curl -u $(head -n 1 ~/.kgauth) -k -F file=@register10-01-18.csv http://java-dev.freecode.no/krigsgraver/person/upload
curl -u $(head -n 1 ~/.kgauth) -k -F file=@tilbud5.utf8 http://java-dev.freecode.no/krigsgraver/postalDistrict/upload
