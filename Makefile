build:
	mvn clean package -DskipTests

run:
	docker-compose up --build