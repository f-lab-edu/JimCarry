#!/bin/bash

DEPLOY_PATH="/home/root/deploy/JimCarry/target"  # 배포 경로
JAR_NAME=$(ls -1 $DEPLOY_PATH/*.jar | head -n 1)
PROFILE_FILE="$DEPLOY_PATH/current_profile.txt"
NGINX_CONF="/etc/nginx/nginx.conf"

# .jar 파일 존재 여부 점검
if [ ! -f "$JAR_NAME" ]; then
  echo "ERROR: $JAR_NAME not found!"
  exit 1
fi

# 현재 프로파일 추적
if [ -f "$PROFILE_FILE" ]; then
  CURRENT_PROFILE=$(cat "$PROFILE_FILE")
else
  CURRENT_PROFILE="blue"
  echo "blue" > "$PROFILE_FILE"
fi

if [ "$CURRENT_PROFILE" == "blue" ]; then
  INACTIVE_PROFILE="green"
  INACTIVE_PORT=8091
  ACTIVE_PORT=8090
else
  INACTIVE_PROFILE="blue"
  INACTIVE_PORT=8090
  ACTIVE_PORT=8091
fi

echo "Switching to $INACTIVE_PROFILE profile..."
echo "$INACTIVE_PROFILE" > "$PROFILE_FILE"

# 현재 nginx가 서비스 중이지 않은 포트 프로세스 종료
INACTIVE_PID=$(ps aux | grep 'java -jar' | grep "spring.profiles.active=$INACTIVE_PROFILE" | awk '{print $2}')

if [ -n "$INACTIVE_PID" ] && ps -p $INACTIVE_PID > /dev/null; then
        echo "Stopping old application (INACTIVE_PID: $INACTIVE_PID)..."
        kill -15 $INACTIVE_PID
        sleep 10  # 충분히 대기
        if ps -p $INACTIVE_PID > /dev/null; then
          echo "INACTIVE application did not stop, forcing kill..."
          kill -9 $INACTIVE_PID
        fi
        sleep 5
fi

# JAR 파일 실행 가능하도록 권한 부여
chmod +x "$JAR_NAME"

# nginx가 서비스 중이지 않은 애플리케이션 실행
echo "Starting new application on port $INACTIVE_PORT with profile $NEW_PROFILE..."
nohup java -jar -Dspring.profiles.active="$INACTIVE_PROFILE" "$JAR_NAME" > "$DEPLOY_PATH/output.log" 2>&1 &
NEW_PID=$!
echo "Captured PID: $NEW_PID"

# Health check
echo "Waiting for application to start on port $INACTIVE_PORT..."

# 최대 30초까지 기다림 (5초마다 확인)
for i in {1..6}; do
  RESPONSE=$(curl -s -m 10 "http://127.0.0.1:$INACTIVE_PORT/actuator/health")
  echo "Response: $RESPONSE"

  if echo "$RESPONSE" | grep -q '"status":"UP"'; then
    echo "New application started successfully on port $INACTIVE_PORT."
    break
  fi

  echo "Retrying in 5 seconds..."
  sleep 5
done

# 서버가 응답하지 않으면 에러 메시지 출력 후 종료
if [ $i -eq 6 ]; then
  echo "ERROR: Application did not start properly within the timeout period."
  exit 1
fi

# nginx의 Port Switching
echo "Switching to $INACTIVE_PROFILE profile..."
echo "set \$service_url http://127.0.0.1:${INACTIVE_PORT};" |sudo tee /etc/nginx/conf.d/service-url.inc
echo "$INACTIVE_PROFILE" > "$PROFILE_FILE"

# Nginx 리로드 (애플리케이션이 정상적으로 시작된 후에만 실행)
echo "Reloading Nginx to switch traffic to the new instance..."
sudo nginx -s reload

# 배포 완료 메시지 출력
echo "Deployment completed! Current profile: $INACTIVE_PROFILE"
