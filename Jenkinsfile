@Library('platform-jenkins-library') _

lock("travel-stubs"){

  node('master') {
        def projectName      = "travel-stubs"
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
       
        stage("Sbt Reload"){
          sh """
            sbt -DverNumber=${version} -DstubName=${stub} reload clean assembly
          """
        }
        
        stage("Publish"){
            //version clean up in s3 if already exist
            sh "aws s3 rm --recursive s3://mvn-artifacts/release/com/telegraph/stub/${stub}/${stub}_2.11/${version}"
            
            sh """
                sbt -DverNumber=${version} -DstubName=${stub} publish
            """
            docker.withRegistry("${env.AEM_DOCKER_REGISTRY}") {
                docker.build("${stub}", "--build-arg APP_VERSION=${version} --build-arg APP_NAME=${stub} ." )
                .push()
            }
        }
        
        stage("Cycle Env"){ 
          sh """ 
             ssh build@aem-docker-${environment}.aws-preprod.telegraph.co.uk uptime
             """
        }
    
        
   }
}

