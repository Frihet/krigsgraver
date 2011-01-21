#! /bin/bash

function warning() {
        echo $@
        echo -n "Do you really want to do this (y/n)? "
        read answer
        if [ "$answer" != "y" ] ; then
            exit 1
        fi
}

warning "Deleting work dirs and redeploying tomcat"

ssh tomcat@java-dev "/etc/init.d/tomcat stop"

#ssh tomcat@java-dev "rm -rf /srv/krigsgraver/lucene"
ssh tomcat@java-dev "rm -rf /srv/tomcat/work/Catalina/localhost/krigsgraver /srv/tomcat/webapps/krigsgraver"

mvn clean package
scp target/krigsgraver.war tomcat@java-dev:/srv/tomcat/webapps/

ssh tomcat@java-dev "/etc/init.d/tomcat start"

echo -n "Waiting for the application to load"
ssh tomcat@java-dev '\
while [ "$(curl -sIL http://java-dev/krigsgraver/inc/styles/main.css | head -n 1 | grep 200)" == "" ]; do \
    echo -n "." ;\
    sleep 1s ;\
done \
'

#echo
#echo "Inserting data..."
#cd notes
#./prepare_remote.sh
