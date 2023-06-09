pipelineJob('pipelineJob') {
    definition {
        cps {
            script(readFileFromWorkspace('pipelineJob.groovy'))
            sandbox()
        }
    }
}

pipelineJob('1kappa-job') {
    definition {
        cpsScm {
            scm {
                git {
                    branches([[name: 'main']])
                    userRemoteConfigs([[credentialsId: 'buddywood', url: 'git@github.com:buddywood/OneKappaWeb.git']])
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}

pipelineJob('1kappa-job-docker') {
    definition {
        cpsScm {
            scm {
                git {
                    branches([[name: 'main']])
                    userRemoteConfigs([[credentialsId: 'buddywood-github', url: 'git@github.com:buddywood/OneKappaWeb.git']])
                }
            }
            scriptPath('Jenkinsfile-docker')
        }
    }
}

pipelineJob('1kappa-job-aws') {
    definition {
        cpsScm {
            scm {
                git {
                    branches([[name: 'main']])
                    userRemoteConfigs([[credentialsId: 'buddywood-github', url: 'git@github.com:buddywood/OneKappaWeb.git']])
                }
            }
            scriptPath('Jenkinsfile-aws')
        }
    }
}
