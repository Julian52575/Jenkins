def call(Map config = [:] ) {
    def boolean hasCompiled = 0
    def boolean hasMakefile = false
    def String compilationLog = ""
    
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
    hasCompiled = sh (
        script: 'make',
        returnStatus: true
    ) == 0
    compilationLog = sh (
        script: 'make || true',
        returnStdout: true
    )
    sh "echo '>>make\n${compilationLog}\n' >> ${config.depthName}"
    if ( hasCompiled == false ) {
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
