def call(Map config = [:] ) {
    hasCompiled = 0

    sh 'echo -n Compilation:\t > new_mouli_log.txt'
    hasCompiled = sh (
        script: 'test Makefile',
        returnStatus: true
    )
    hasCompiled = sh (
        script: 'make',
        returnStatus: true
    )
    hasCompiled = sh (
        script: "test -x ${config.name}",
        returnStatus: true
    )
    if ( hasCompiled == 0 ) {
        sh (
            script: "echo '${config.name} has been correctly compiled !' > new_mouli_log.txt",
            returnStatus: true
        )
    } else {
        sh "echo K.O No compilado :( > new_mouli_log.txt"
    }
    return hasCompiled
}
