@Library("CompilationLibrary") _
pipeline {
    agent any

    parameters {
        string(name: 'ProjectName', defaultValue: 'Incredible Math')
        string(name: 'Author', defaultValue: 'Julian Bottiglione', description: 'he who started it all')
        string(name: 'Email', defaultValue: 'julian.bottiglione@epitech.eu', description: 'the email that will receive the log')
    }

    environment {
        hasCompiled = 0
    }

    stages {
        
        stage("Hello world") {
            steps {
                sh( 'echo "Starting Jenkinsfile.."' )
            }
        }

        stage("Check Basics") {
            when {
                environment name: 'hasCompiled', value: '0'
            }
            steps {
                checkBasics(
                    name:"math",
                    author:params.Author
                )
            }
        }

        stage("Check in-depth") {
            steps {
                sh "echo Starting Check in-depth"
                printTable() 
                runTest(
                    name:"1+1",
                    cmd:"./math + 1 1 ",
                    expOutput:"Your result is 2.",
                    expReturnValue:0
               )

                runTest(
                    name:"a+b",
                    cmd:"./math + a b ",
                    expOutput:"[Error] Invalid number 1.",
                    expReturnValue:84
                )


            }
        }
    }
    post {
        always {
            sendEmailReport( projectName:params.ProjectName )
            
            // Clean after build
            cleanWs(cleanWhenNotBuilt: true,
                    deleteDirs: true,
                    disableDeferredWipeout: false)
        }
    }
}
