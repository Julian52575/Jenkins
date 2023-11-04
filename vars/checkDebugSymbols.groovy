def call(Map config = [:]) {
    hasDebugSymbol = sh (
        script: "file ${config.name} | grep -v 'with debug_info'",
        returnStatus: true
    )
    //Logging
    sh "echo -n '[[[Debug symbol:\t' >> ${config.logName}"
    if ( hasDebugSymbol == "0" ) {
        printOK()
        sh "echo -n 'No debbuging symbols present in binary.' >> ${config.logName}"
    } else {
        printKO()
        sh "echo -n 'Binary has been compiled with debbuging info.' >> ${config.logName}"
    }
    sh "echo '\n' >> ${config.logName}"
    //
    return hasDebugSymbol
}
