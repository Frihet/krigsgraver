#! /bin/bash

curl -u $(head -n 1 ~/.kgauth) http://java-dev.freecode.no/krigsgraver/admin/insertBaseData > /dev/null
