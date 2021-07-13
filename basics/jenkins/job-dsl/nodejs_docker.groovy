job('NodeJS Docker example') {
    scm {
        git('git://github.com/mishab00/docker-cicd.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        dockerBuildAndPublish {
            buildContext('basics')
            repositoryName('bmisha00/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('bmisha00')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
