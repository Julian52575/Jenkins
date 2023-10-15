def call(Map config = [:] ) {
    def dockerImage = "ghcr.io/epitech/coding-style-checker:latest"

    sh 'echo "Starting Coding Style"'
    agent {
        docker {
            image dockerImage
            args ". ."
        }
    }
    steps {
        sh ". ."
    }


    return 2
}



//load docker image + container
//run coding style & bind report
//count how much error
//
//
//send report to Email
