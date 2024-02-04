// config.cmd -> command to run
// config.expOutput -> expected output to stdin
// config.expStatus -> expected return status
// config.logPath -> logFile to write result into (if any)
// config.errorPath -> file to log errors into

def call(Map config = [:]) {
    def hasCompiled = 0
    def stdOutput = ""
    def status = 0
    def expOutput = config.expOutput
    def expStatus = config.expStatus
    def logPath = config.logPath
    def errorPath = config.errorPath

    //Run command and store in classResult
    //https://stackoverflow.com/questions/159148/groovy-executing-shell-commands
    sh "echo Executing ${config.cmd}..."//////
    def classResult  = "${config.cmd}".execute()
    stdOutput = classResult.text
    //status = classResult.status()

    /*
    if ( status != 0 )
        return status
    stdOutput = readFile('tmp.txt').trim()
    if ( ${config.logname} == "" ) {
      sh "echo 'Result: _${stdOutput}_' "
    } else {
      sh "echo 'Ok _${stdOutput}_' >> ${logPath}"
    }
    */
    return 0
}
