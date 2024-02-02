// config.cmd -> command to run
// config.logName -> logFile to write result into (if any)
// config.errorFile -> file to log errors into
def call(Map config = [:]) {
    def hasCompiled = 0
    def classResult
    def stdOutput = ""
    def status = 0

    sh "echo Executing ${config.cmd}"//////
    
    classResult  = "${config.cmd}".execute()
    stdOutput = classResult.text()
    //status = classResult.status()

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
