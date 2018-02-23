@Library('platform-jenkins-library') _

lock("travel-stubs"){

  node('master') {
        
        def projectName         = "travel-stubs"
        def github_token        = "${env.GITHUB_TOKEN}"
        def branchName          = "${env.BRANCH_NAME}"
        def buildNumber         = "${env.BUILD_NUMBER}"
        def githubToken         = "${env.GITHUB_TOKEN}"
        def slackToken          = "${env.SLACK_TRAVEL_ALERTS}"
        def pipelineVersion     = "1.0.0-b${env.BUILD_NUMBER}"
        def jenkinsGithubId     = "${env.JENKINS_GITHUB_CREDENTIALS_ID}"
        def environment         = "${environment}" 
        def stub                = "${stub}"
         
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
        
        stage("Notify Slack"){
                    gitCommit = sh(returnStdout: true, script: "git log -1 --pretty=format:'%h %an %s'").trim()
                    gitFullSha1 = sh(returnStdout: true, script: "git rev-parse HEAD").trim()

                    slackSend message: "${stub} - Image built and pushed to aem repo. Most recent commit: \n${gitCommit}(<https://github.com/telegraph/${projectName}/commits/${gitFullSha1}|Open>)", token: "${env.SLACK_TRAVEL_ALERTS}", channel: "#travel_alerts", teamDomain: "telegraph", baseUrl: "https://hooks.slack.com/services/", color: "good"
        } 
       
        withEnv(["BUILD_NUMBER=${buildNumber}-preprod"]){
                notifyOfAction {
                    action       = "${stub} deploy"
                    channel      = "#travel_alerts"
                    shell_action = """
                       aws --region eu-west-1 --profile preprod ec2 terminate-instances --instance-ids `ssh -o StrictHostKeyChecking=no build@aem-docker-${environment}.aws-preprod.telegraph.co.uk 'curl http://169.254.169.254/latest/meta-data/instance-id'`
                    """
                    slack_token  = slackToken
                }
        }
        
        stage("Post Notify"){
                    slackSend message: "${environment} respin is in progress, it will take min 15 mintues for the it to be fully up and running. Most recent commit: \n${gitCommit}(<https://github.com/telegraph/${projectName}/commits/${gitFullSha1}|Open>)", token: "${env.SLACK_TRAVEL_ALERTS}", channel: "#travel_alerts", teamDomain: "telegraph", baseUrl: "https://hooks.slack.com/services/", color: "good"
        } 
        
    
        
   }
}
