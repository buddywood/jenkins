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
                    remote {
                        url 'https://github.com/buddywood/OneKappaWeb.git'
                    }
                    branch 'master'
                }
            }
        }
    }
}