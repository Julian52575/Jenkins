// config.cmd -> command to run
// config.expOutput -> expected output to stdin
// config.expStatus -> expected return status
// config.logPath -> logFile to write result into (if any)
// config.errorPath -> file to log errors into

def call(Map config = [:]) {
    if ( config.cmd == "" )
        return 84

    sh "echo Executing ${config.cmd}..."/////////////////////////
    def expOutput = config.expOutput
    def expStatus = config.expStatus
    def logPath = config.logPath
    def errorPath = config.errorPath
    def status = 0
    def stdOutput = ""
    //Run command thanks to java.lang.Process
    def process = "${config.cmd}".execute().waitFor()
    //Timeout: No documentation, no timeout //process.waitFor(5, java.util.concurrent.TimeUnit.MINUTES)
    stdOutput = process.text
    status = process.exitValue()
    sh "echo ${status}: _${stdOutput}_"//////////////////////////

    if ( status != 0 )
        return status
    if ( ${config.logname} == "" ) {
      sh "echo 'Result: _${stdOutput}_' "
    } else {
      sh "echo 'Ok _${stdOutput}_' >> ${logPath}"
    }
    return 0
}
