pipeline {
    agent {
        dockerfile {
            dir 'docker'
            filename 'Dockerfile'
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
