def call(Map config = [:] ) {
    hasCompiled = 0

    sh "echo -n '[[[Compilation: \t' >> ${config.logName}"
    //TEST MAKEFILE
    def hasMakefile = sh (
        script: 'test Makefile',
        returnStatus: true
    )
    if ( hasMakefile != 0 ) {
        printKO()
        sh "echo 'You don\'t even have a Makefile ??' >> ${config.logName}"
        exit 84
    }
    //MAKE
    def hasCompiled = sh (
        script: 'make',
        returnStatus: true
    )
    if ( hasCompiled != 0 ) {
        printKO()
        sh "echo 'Compilation failed with status ${hasCompiled}:' >> ${config.logName}"
        def compilationLog = sh (
            script: 'make || true',
            returnStdout: true
        )
        sh "echo '${compilationLog}' >> ${config.logName}"
        exit 84
    }
    //TEST -X 
    def isExecutable = sh (
        script: "test -x ${config.name}",
        returnStatus: true
    )
    if ( isExecutable != 0 ) {
        printKO()
        sh "echo 'File or Execute bit missing (for real ?).' >> ${config.logName}"
        exit 84
    }
    //LOGGING
    printOK()
    sh "echo '${config.name} has been compiled succesfully!' >> ${config.logName}"
    return 0
}
