#! /bin/bash

curl -u $(head -n 1 ~/.kgauth) http://localhost:8080/krigsgraver/admin/insertBaseData > /dev/null
