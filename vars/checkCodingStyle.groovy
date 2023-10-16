def call(Map config = [:] ) {
    def dockerImage = "ghcr.io/epitech/coding-style-checker:latest"

    sh 'echo "Starting Coding Style"'
    sh 'docker run ghcr.io/epitech/coding-style-checker:latest . .'
    sh 'cat coding-style-reports.log'
    return 2
}



//load docker image + container
//run coding style & bind report
//count how much error
//
//
//send report to Email
