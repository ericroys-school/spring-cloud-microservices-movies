pipeline {
    agent {
        docker {
            image 'eclipse-temurin:21-jdk-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }

    stages {
        stage('Initialize'){
            steps{
                sh 'RUN apk add --no-cache maven'
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }

        }
    }
}
