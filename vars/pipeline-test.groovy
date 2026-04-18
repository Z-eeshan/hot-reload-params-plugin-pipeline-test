pipeline {
    agent master

    parameters {
        string(name: 'RELEASE_BRANCH', defaultValue: 'main', description: 'Branch to load parameters from')
        string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: 'Target deployment environment')
        string(name: 'API_VERSION', defaultValue: 'v1', description: 'API version to deploy')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip the test suite')
        booleanParam(name: 'NOTIFY_SLACK', defaultValue: true, description: 'Send Slack notification on completion')

        hotReloadParams(
            repoUrl: 'https://github.com/your-org/your-repo.git',
            paramFilePath: 'vars/pipeline-test.groovy',
            triggerParamName: 'RELEASE_BRANCH',
            defaultBranch: 'main'
        )
    }

    stages {
        stage('Build') {
            steps {
                echo "Building branch: ${params.RELEASE_BRANCH}"
                echo "Deploy env: ${params.DEPLOY_ENV}"
                echo "API version: ${params.API_VERSION}"
            }
        }
    }
}