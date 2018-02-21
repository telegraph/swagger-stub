@Library('platform-jenkins-library') _

lock("cruise-stub"){

  node('master') {
       // def sbtFolder        = "${tool name: 'sbt-1.1.1', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin"
        def projectName      = "cruise-stub"
        def github_token     = "${env.GITHUB_TOKEN}"
        def branchName       = "${env.BRANCH_NAME}"
        def buildNumber      = "${env.BUILD_NUMBER}"
        def githubToken         = "${env.GITHUB_TOKEN}"
        def slackToken          = "${env.SLACK_PLATFORMS_CI}"
        def pipelineVersion     = "1.0.0-b${env.BUILD_NUMBER}"
        def jenkinsGithubId     = "${env.JENKINS_GITHUB_CREDENTIALS_ID}"
        
        checkoutCodeStage {
            project_name = projectName
            github_id    = jenkinsGithubId
        }
        
        stage("Checkout"){
        echo "git checkout"
        checkout changelog: false, poll: false, scm: [
            $class: 'GitSCM', 
            branches: [[
                name: 'master'
            ]],
            doGenerateSubmoduleConfigurations: false, 
            extensions: [[
                $class: 'WipeWorkspace'
            ], [
                $class: 'CleanBeforeCheckout'
            ]], 
            submoduleCfg: [], 
            userRemoteConfigs: [[
                credentialsId: 'fe000f7c-4de6-45c7-9097-d1fba24f3cb5', 
                url: "git@github.com:telegraph/${projectName}.git"
            ]]
          ]
   	    }
         
        stage("Build & Test"){
          sh """
            sbt clean test package
          """
        }

        stage("Publish"){
            sh """
                sbt publish
                sbt assembly
            """
            docker.withRegistry("${env.AEM_DOCKER_REGISTRY}") {
                docker.build("${projectName}")
                .push()
            }
        }
    
        
   }
}
