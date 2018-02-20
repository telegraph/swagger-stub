node('master') {

        def projectName      = "cruise-stub"
        def github_token     = "${env.GITHUB_TOKEN}"
        def jenkins_github_id= "${env.JENKINS_GITHUB_CREDENTIALS_ID}"
        def branchName       = "${env.BRANCH_NAME}"
        def buildNumber      = "${env.BUILD_NUMBER}"


        checkoutCodeStage {
            project_name  = projectName
            github_id     = jenkinsGithubId
            github_branch = branchName
        }

        cloudformationValidateStage{}

        if (branchName == 'master') {

            def pipeline_version = "1.0.0-b${buildNumber}-${environment}"

            stage("Submodule checkout") {
                sh "git submodule update --init --recursive"
                sh "git submodule foreach git pull origin master"
            }


            stage("Build"){
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
