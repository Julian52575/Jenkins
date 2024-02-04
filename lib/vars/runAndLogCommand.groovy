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
    transient def status = 0
    transient def stdOutput = ""
    transient def process = null
    //Run command thanks to java.lang.Process
    try {

        process = "${config.cmd}".execute()

        transient def bob = process.isAlive()
        if ( bob == true ) {
            echo "${config.cmd}:\tProcess still running."
        } else {
            echo "${config.cmd}:\tProcess stoped."
        }

        process.waitFor()
        echo "${config.cmd}:\twaitFor successful."
        
        status = process.exitValue()
        echo "${config.cmd}:\tExit value successful."
        echo "Exit value:\t${status}."
        
        stdOutput = process.getText().trim()
        echo "${config.cmd}:\tText succesful."
        echo "Get Text:\t${stdOutput}."
        
    } catch (Exception e) {
        echo "!!! Exception: ${e.message}"
    }
    return 0
}
