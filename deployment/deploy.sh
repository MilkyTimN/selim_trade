#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/selim_trade-0.0.1-SNAPSHOT.jar \
  team4@161.35.29.179:/home/team4

echo 'Restart Server...'

ssh -i ~/.ssh/id_rsa team4@161.35.29.179 << EOF

pgrep java | xargs kill -9
nohup java -jar selim_trade-0.0.1-SNAPSHOT.jar &

EOF

echo 'Bye!'