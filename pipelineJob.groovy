pipeline {
    agent any
    tools {
        gradle 'gradle' // Use the default or configured Gradle installation
    }
        stages {
        stage('Print Working Directory') {
            steps {
                script {
                    def currentDir = pwd()
                    println "Current working directory: ${currentDir}"
                }
            }
        }
        stage('Build') {
            steps {
                echo 'Build'
            }
        }
        stage('Test'){
            steps {
                echo 'Test'
            }
        }
    }
}