// config.cmd -> command to run
// config.logName -> logFile to write result into (if any)
def call(Map config = [:]) {
    hasCompiled = 0
    stdOutput = ""
  
    stdOutput = sh (
        script: '${config.cmd}',
        returnStdout: true 
    ) == 0
    if ( ${config.logname} == "" ) {
      sh "echo 'Result: _${stdOutput}_' "
    } else {
      sh "echo 'Ok _${stdOutput}_' >> ${config.logName}"
    }
    return 0
}
