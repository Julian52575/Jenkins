def call(Map config = [:] ) {
    def int hasCompiled = 0
    def boolean hasMakefile = false
    
    sh "echo -n '*Compilation: \t' >> ${config.logName}"
    //TEST MAKEFILE
    hasMakefile = sh (
        script: 'test Makefile',
        returnStatus: true
    ) == 0
    if ( hasMakefile == false ) {
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
    return 0
}
