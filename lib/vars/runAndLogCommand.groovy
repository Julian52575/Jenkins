// config.cmd -> command to run
// config.logName -> logFile to write result into (if any)
// config.errorFile -> file to log errors into
def call(Map config = [:]) {
    hasCompiled = 0
    stdOutput = ""
    status = 0

    sh "echo Executing ${config.cmd}"//////
    status = sh (
        script: '${config.cmd} > tmp.txt',
        returnStatus: true 
    )
    if ( status != 0 )
        return status
    stdOutput = readFile('tmp.txt').trim()
    if ( ${config.logname} == "" ) {
      sh "echo 'Result: _${stdOutput}_' "
    } else {
      sh "echo 'Ok _${stdOutput}_' >> ${config.logName}"
    }
    return 0
}
