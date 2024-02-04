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
        if ( logPath == "" )
            logPath = "Result.log"
    def status = 0 //COmes from java stuff, no cast to sh compatible
    def stdOutput = ""
    def process = null
    
    //Run command thanks to java.lang.Process (fuck the documentation tho)
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

    echo "Testing expOutput."
    def boolean outputResult = false
    if ( stdOutput == expOutput ) {
        outputResult = true
    }
    echo "Testing expStatus."
    def boolean statusResult = false
    if ( status == expStatus ) {
        statusResult = true
    }

    writeFile (
        file: logPath,
        text: ">> ${config.cmd}:\t\t"
    )    
    if ( statusResult == true && outputResult == true ) {
        writeFile (
            file: logPath,
            text: "OK.\n"
        )
    } else {
            writeFile (
                file: logPath,
                text: "KO"
            )
        if ( outputResult == false ) {
            writeFile (
                file: logPath,
                text: "Expected output in stdout:\n${expOutput}\nBut got:\n${stdOutput}.\n"
            )
        }
        if ( statusResult == false ) {
            writeFile (
                file: logPath,
                text: "Expected return status:\n${expStatus}\nBut got:\n${status}.\n"
            )
        }
    }
    writeFile (
            file: logPath,
            text: "(^'o')^  ^('o'^)  ^('o'^)^('o'^)"
    )
    return 0
}
