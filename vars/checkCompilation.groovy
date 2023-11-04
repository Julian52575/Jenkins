def call(Map config = [:] ) {
    hasCompiled = 0

    sh "echo -n '*Compilation: \t' >> ${config.logName}"
    //TEST MAKEFILE
    def hasMakefile = sh (
        script: 'test Makefile',
        returnStatus: true
    )
    if ( hasMakefile != 0 ) {
        printKO(
            logName: "${config.logName}"
        )
        sh "echo 'You don\'t even have a Makefile ??' >> ${config.logName}"
        return 84
    }
    //MAKE
    def hasCompiled = sh (
        script: 'make',
        returnStatus: true
    )
    def compilationLog = sh (
        script: 'make || true',
        returnStdout: true
    )
    sh "echo '>>make\n${compilationLog}\n' >> ${config.depthName}"
    if ( hasCompiled != 0 ) {
        printKO(
            logName: "${config.logName}"
        )
        sh "echo 'Compilation failed with status ${hasCompiled}:' >> ${config.logName}"
        return 84
    }
    //TEST -X 
    //def isExecutable = sh (
    //    script: "test -x ${config.name}",
    //    returnStatus: true
    //)
    //if ( isExecutable != 0 ) {
    //    printKO(
    //      logName: "${config.logName}"
    //    )
    //    sh "echo 'File or Execute bit missing (for real ?).' >> ${config.logName}"
    //    return 84
    //}
    //LOGGING
    printOK(
         logName: "${config.logName}"
   )
    sh "echo '${config.name} has been compiled succesfully!' >> ${config.logName}"
    return 0
}
