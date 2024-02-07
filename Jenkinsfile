pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21'
            args '-u root'
        }
    }

    stages {

        stage('Build') { 
            steps {
                sh '''cd movie-rating* && mvn -B -DskipTests clean package
                && cd ../movie-info* && mvn -B -DskipTests clean package
                && cd ../movie-cat* && mvn -B -DskipTests clean package
                && cd ../discovery* && mvn -B -DskipTests clean package'''
            }
        }
        stage('Test') {
            steps {
                sh 'cd movie-rating* mvn test'
            }

        }
    }
}
