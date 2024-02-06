pipeline {
    agent {
        dockerfile {
            dir 'docker'
            filename 'Dockerfile'
            args '-v /opt/.m2:/opt/.m2'
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
