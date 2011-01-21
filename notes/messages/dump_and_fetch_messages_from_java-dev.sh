#! /bin/bash

ssh tomcat@java-dev "~/translations/dump_files.sh"
scp -r tomcat@java-dev:/tmp/krigsgraver_messages/* .
