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
                 mvn -B -DskipTests clean package'''
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
        stage('Deploy'){
            agent {
                docker {
                    image 'docker:25.0.3-cli-alpine3.19'
                    args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
                    reuseNode true
                }
            }
            steps{
                sh '''
                pwd
                cd disc*
                pwd
                rm -rf dbuild 
                mkdir dbuild 
                cd dbuild
                ls ../..
                cp ../target/discovery-server-0.0.1-SNAPSHOT.jar .
                docker build -t eureka/server:latest . -f ../../docker/discovery.dockerfile
                docker tag eureka/server 192.168.1.170:5000/eureka/server
                docker push 192.168.1.170:5000/eureka/server
                '''
            }
            /*

                            sh '''
                cd movie-rating*
                mvn clean install
                cd target
                //cp movie-ratings-service-0.0.1-SNAPSHOT.jar /build_out
                cd ../../disc*
                mkdir dbuild
                cd dbuild cp ../target/discovery-server-0.0.1-SNAPSHOT.jar .
                mvn clean install
                cd target 
                docker build -t eureka/discovery-service:latest 
                //cp discovery-server-0.0.1-SNAPSHOT.jar /build_out
                '''*/

        }
    }
}
