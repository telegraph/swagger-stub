node('master') {

        def projectName      = "cruise-stub"
        def github_token     = "${env.GITHUB_TOKEN}"
        def jenkins_github_id= "${env.JENKINS_GITHUB_CREDENTIALS_ID}"
        def branchName       = "${env.BRANCH_NAME}"
        def buildNumber      = "${env.BUILD_NUMBER}"

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


        stage("Build"){

            def pipeline_version = "1.0.0-b${buildNumber}-${environment}"

            sh """
                    echo "Build"
                """
                app = docker.build("${projectName}:${pipeline_version}")


            }


        stage("Publish"){
            sh """
                    echo "Publish"
                """
                docker.withRegistry("http://docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub") {
                    app.push()
                }
            }

        }
}
