FROM openjdk:17.0.2-jdk-slim-buster AS builder

WORKDIR /app

# 먼저 Gradle 설정 파일을 복사 (의존성 캐싱을 위해)
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# 의존성 다운로드 (소스 코드 없이 먼저 실행하여 캐싱 활용)
RUN ./gradlew dependencies --no-daemon

COPY src src

# 빌드 실행 (테스트는 제외)
RUN ./gradlew clean build -x test --no-daemon

# 실행 단계
FROM openjdk:17.0.2-slim-buster

WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]