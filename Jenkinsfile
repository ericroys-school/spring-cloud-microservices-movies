pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage ('Initialize') {
            steps {
                sh "if java -version 2>&1 | grep 'version "19"'; sudo apt-get install openjdk-19-jdk"
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
