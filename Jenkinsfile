pipeline {
    agent {
        docker {
            image 'eclipse-temurin:21-jdk-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }

    stages {
        stage ('Initialize') {
            steps {
                sh '''
                echo "DIR = ${PWD}"
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
                echo "JAVA = ${java -version}
                '''
            }
        }          
        stage('Build') {
            steps {
                // Get micro-service code which is separate project folder in one repo
                git 'https://github.com/ericroys/micro-service-playground.git'
                //start with build of discovery server
                cd discovery-server
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
