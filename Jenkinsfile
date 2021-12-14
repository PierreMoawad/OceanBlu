pipeline {
    agent any
    stages {
        stage("Install") {
            steps {
                sh 'mvn clean install'
            }
            post{
                success{
                    updateGitlabCommitStatus name: 'Install', state: 'success'
                }
                failure{
                    updateGitlabCommitStatus name: 'Install', state: 'failed'
                }
            }
        }
        stage("Dockerize") {
            steps {
                sh 'docker-compose up --no-start'
            }
            post{
                success{
                    updateGitlabCommitStatus name: 'Dockerize', state: 'success'
                }
                failure{
                    updateGitlabCommitStatus name: 'Dockerize', state: 'failed'
                }
            }
        }
    }
}