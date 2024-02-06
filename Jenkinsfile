pipeline {
    agent {
        dockerfile {
            filename 'docker/Dockerfile'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {

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
