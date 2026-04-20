pipeline {
    agent any

    parameters {
        string(name: 'RELEASE_BRANCH', defaultValue: 'main', description: 'Branch to load parameters from')
        string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: 'Target deployment environment')
        string(name: 'API_VERSION', defaultValue: 'v1', description: 'API version to deploy')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip the test suite')
        booleanParam(name: 'NOTIFY_SLACK', defaultValue: true, description: 'Send Slack notification on completion')
        choice(name: 'BUILD_TYPE', choices: ['Release', 'Debug', 'Profile'], description: 'Select build type')
        choice(name: 'REGION', choices: ['us-east-1', 'us-west-2', 'eu-central-1', 'ap-southeast-1'], description: 'AWS region for deployment')
        text(name: 'RELEASE_NOTES', defaultValue: '', description: 'Multi-line release notes')
        text(name: 'CONFIG_OVERRIDES', defaultValue: '', description: 'Optional configuration overrides (JSON format)')
        password(name: 'API_TOKEN', defaultValue: '', description: 'API authentication token')
        password(name: 'DB_PASSWORD', defaultValue: '', description: 'Database password for migration')
        
        hotReloadParams(
            repoUrl: 'https://github.com/Z-eeshan/hot-reload-params-plugin-pipeline-test.git',
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
                echo "Build type: ${params.BUILD_TYPE}"
                echo "Region: ${params.REGION}"
                echo "Skip tests: ${params.SKIP_TESTS}"
                echo "Release notes: ${params.RELEASE_NOTES}"
                echo "Config overrides: ${params.CONFIG_OVERRIDES}"
                echo "Notify Slack: ${params.NOTIFY_SLACK}"
                echo "API Token: ${params.API_TOKEN ? '****' : 'Not provided'}"
                echo "DB Password: ${params.DB_PASSWORD ? '****' : 'Not provided'}
            }
        }
    }
}