stop-postgres-container:
	-docker rm -f postgres-1

delete-postgres-image:
	-docker rmi postgres-image-1

build-postgres-image:
	docker build -t postgres-image-1 .

run-postgres-container:
	docker run --name postgres-1 -p 5432:5432 -d postgres-image-1
