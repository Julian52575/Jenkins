def call(Map config = [:] ) {
    def boolean hasCompiled = false
    def boolean hasMakefile = false
    def boolean isExecutable = false
    def String boolStrBuffer = "0"
    def String compilationLog = ""
    def String logPath = config.logPath
    if (logPath == null)
        logPath = "Result.log"
    def String binaryName = config.binaryName
    if (binaryName == null)
        return false
    
    sh "echo -n '*Compilation: \t' >> ${logPath}"
    //TEST MAKEFILE
    hasMakefile = sh (
        script: 'test Makefile',
        returnStatus: true
    ) == 0
    if ( hasMakefile == false ) {
        printKO(
            logName: logPath
        )
        sh "echo 'KO. No Makefile are present in the workspace.' >> ${logPath}"
        return false
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
    if (compilationLog.contains("Warning") == true) {
        sh "echo 'KO. Compiled with Warnings.\n${compilationLog}' > ${logPath}"
        return false
    }
    if ( hasCompiled == false ) {
        printKO(
            logName: logPath
        )
        sh "echo 'KO. Compilation failed with status non 0.' >> ${logPath}"
        return false
    }
    /*TEST -X 
    /isExecutable = sh (
        script: "test -x ${binaryName}",
        returnStatus: true
    ) == 0
    if ( isExecutable == false ) {
        printKO(
          logName: logPath
        )
        sh "echo '${binaryName}' >> ${logPath}"
        return false
    }*/
    sh "echo 'OK.' >> ${logPath}"
    return true
}
