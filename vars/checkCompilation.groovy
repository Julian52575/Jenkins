def call(Map config = [:] ) {
    hasCompiled = 0

    sh 'echo -n "[[[Compilation: \t" >> new_mouli_log.txt'
    //TEST MAKEFILE
    hasCompiled = sh (
        script: 'test Makefile',
        returnStatus: true
    )
    if ( hasCompiled != 0 ) {
        printKO()
        sh 'echo "You don\'t even have a Makefile ??"'
        exit hasCompiled
    }
    //MAKE
    hasCompiled = sh (
        script: 'make',
        returnStatus: true
    )
    if ( hasCompiled != 0 ) {
        printKO()
        sh 'echo "Compilation failed."'
        exit hasCompiled
    }
    //TEST -X 
    hasCompiled = sh (
        script: "test -x ${config.name}",
        returnStatus: true
    )
    if ( hasCompiled != 0 ) {
        printKO()
        sh 'echo "File or Execute bit missing (really ?)."'
        exit hasCompiled
    }
    //LOGGING
    if ( hasCompiled == 0 ) {
        sh (
            script: 'echo "${config.name} has been correctly compiled !" >> new_mouli_log.txt',
            returnStatus: true
        )
    }
    return hasCompiled
}
