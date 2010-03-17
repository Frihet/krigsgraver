#! /bin/bash

curl -u $(head -n 1 ~/.kgauth) -k -F file=@tilbud5.utf8 http://localhost:8080/krigsgraver/postalDistrict/upload
