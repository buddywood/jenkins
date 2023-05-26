pipelineJob('pipelineJob') {
    definition {
        cps {
            script(readFileFromWorkspace('pipelineJob.groovy'))
            sandbox()
        }
    }

pipelineJob('1kappa-job') {
        definition {
            cspScm {
                git {
                    remote {
                        url 'https://github.com/buddywood/OneKappaWeb.git'
                    }
                    branch 'main'
                }
            }

        }
    }
    pipelineJob('1kappa-job-docker') {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url 'https://github.com/buddywood/OneKappaWeb.git'
                        }
                        branch 'main'
                        scriptPath('Jenkinsfile-docker')
                    }
                }
            }
        }
    }
}