pipeline {
    agent any
    stages {
        stage("Maven build") {
            steps {
                sh 'mvn -B clean package'
            }
        }
        stage("Gatling run") {
            steps {
                sh 'mvn clean gatling:test'
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
    }
}