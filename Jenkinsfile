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
                }
            }
            steps {
                sh 'cd movie-rating* mvn test'
            }

        }
        stage('Deploy'){
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                    args '-u root -v $HOME/.m2:/root/.m2 -v /opt/docker_build_dir:/build_out'
                    reuseNode true
                }
            }
            steps{
                sh '''
                cd movie-rating*
                mvn clean install
                cd target
                cp movie-ratings-service-0.0.1-SNAPSHOT.jar /build_out
                cd ../../disc*
                mvn clean install
                cd target 
                cp discovery-server-0.0.1-SNAPSHOT.jar /build_out
                '''
            }

        }
    }
}
