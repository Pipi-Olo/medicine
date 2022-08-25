#!/bin/bash

REPOSITORY=/home/ec2-user/deploy

CURRENT_PID=$(pgrep -f $REPOSITORY/*.jar)
echo "현재 구동 중인 Application Pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 Application 이 없으므로 종료하지 않습니다."
else
  echo "> sudo kill -15 $CURRENT_PID"
  sudo kill -15 "$CURRENT_PID"
  sleep 5
fi

echo "> New Application 배포"

JAR_NAME=$(ls -tr $REPOSITORY/build/libs/*.jar | tail -n 1)
echo "> JAR NAME : $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x "$JAR_NAME"

echo "> $JAR_NAME 실행"
nohup java -jar \
  -server \
  -Dspring.config.location=/home/ec2-user/app/application.yml \
  "$JAR_NAME" 2>&1 &