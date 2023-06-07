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
                        url 'git@github.com:buddywood/OneKappaWeb.git'
                    }
                    branch 'main'
                }
            }
        }
    }
}