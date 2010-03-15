#! /bin/bash

curl http://localhost:8080/krigsgraver/admin/insertUser
curl -u $(head -n 1 ~/.kgauth) -k -F file=@register10-01-18.csv http://localhost:8080/krigsgraver/person/upload
curl -u $(head -n 1 ~/.kgauth) -k -F file=@tilbud5.utf8 http://localhost:8080/krigsgraver/postalDistrict/upload
