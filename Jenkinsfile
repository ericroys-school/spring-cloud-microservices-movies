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
                sh 'cd / && find -name pom.xml && mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }

        }
    }
}
