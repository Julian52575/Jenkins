def call(Map config = [:] ) {
    def dockerImage = "ghcr.io/epitech/coding-style-checker:latest"

    agent {
        docker {
            image dockerImage
            args ". ."
        }
    }
    steps {
        ". ."
    }


    return 2
}



//load docker image + container
//run coding style & bind report
//count how much error
//
//
//send report to Email
