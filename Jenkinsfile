pipeline {
    agent any

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                    args '-u root -v $HOME/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh '''cd movie-rating*
                 mvn -B -DskipTests clean package
                 cd ../movie-info* m
                 mvn -B -DskipTests clean package
                 cd ../movie-cat*
                 mvn -B -DskipTests clean package
                 cd ../discovery*
                 mvn -B -DskipTests clean package
                 cd ../gateway
                 mvn -B -DskipTests clean package
                 '''
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                    args '-u root -v $HOME/.m2:/root/.m2'
                    reuseNode true
                }            }
            steps {
                sh 'cd disc* mvn test'
            }
            }
        stage('Deploy') {
            agent {
                docker {
                    image 'docker:25.0.3-cli-alpine3.19'
                    args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
                    reuseNode true
                }
            }
            steps {
                sh '''
                pwd
                cd disc*
                pwd
                rm -rf dbuild
                mkdir dbuild
                cd dbuild
                ls ../..
                cp ../target/discovery-server-0.0.1-SNAPSHOT.jar .
                cp ../../discovery-server/docker/discovery.dockerfile .
                docker build -t eureka/server:latest . -f ./discovery.dockerfile
                docker tag eureka/server 192.168.1.170:5000/eureka/server
                docker push 192.168.1.170:5000/eureka/server

                cd ../../movie-rating*
                pwd
                rm -rf dbuild
                mkdir dbuild
                cd dbuild
                cp ../target/*.jar .
                cp ../../movie-rating*/docker/Dockerfile .
                docker build -t rating/server:latest . -f ./Dockerfile
                docker tag rating/server 192.168.1.170:5000/rating/server
                docker push 192.168.1.170:5000/rating/server

                cd ../../gateway
                rm -rf dbuild
                mkdir dbuild
                cp ../target/*.jar .
                cp ../../gateway/docker/Dockerfile .
                docker build -t gateway/server:latest . -f ./Dockerfile
                docker tag gateway/server 192.168.1.170:5000/gateway/server
                docker push 192.168.1.170:5000/gateway/server

                cd ../../movie-catalog*
                rm -rf dbuild
                mkdir dbuild
                cp ../target/*.jar .
                cp ../../gateway/docker/Dockerfile .
                docker build -t catalog/server:latest . -f ./Dockerfile
                docker tag catalog/server 192.168.1.170:5000/catalog/server
                docker push 192.168.1.170:5000/catalog/server

                cd ../../movie-info*
                rm -rf dbuild
                mkdir dbuild
                cp ../target/*.jar .
                cp ../../gateway/docker/Dockerfile .
                docker build -t movie-info/server:latest . -f ./Dockerfile
                docker tag movie-info/server 192.168.1.170:5000/movie-info/server
                docker push 192.168.1.170:5000/movie-info/server

                '''
            }
        }
        }
    }
