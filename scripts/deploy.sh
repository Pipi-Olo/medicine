#!/bin/bash

REPOSITORY=/home/ec2-user/deploy
PROJECT_LOCATION=/home/ec2-user/app
PROJECT_NAME=medicine

echo "> Build File 복사"
cp $REPOSITORY/build/libs/*.jar $PROJECT_LOCATION

CURRENT_PID=$(pgrep -fl $PROJECT_LOCATION/$PROJECT_NAME | awk '{print $1}')
echo "> 현재 구동 중인 Application Pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 Application 이 없으므로 종료하지 않습니다."
else
  echo "> sudo kill -15 $CURRENT_PID"
  sudo kill -15 "$CURRENT_PID"
  sleep 5
fi

echo "> New Application 배포"

JAR_NAME=$(ls -tr $PROJECT_LOCATION/*.jar | tail -n 1)
echo "> JAR NAME : $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x "$JAR_NAME"

echo "> $JAR_NAME 실행"
nohup java -jar \
  -server \
  -Dspring.config.location=$PROJECT_LOCATION/application.yml \
  "$JAR_NAME" > $PROJECT_LOCATION/nohup.out 2>&1 &