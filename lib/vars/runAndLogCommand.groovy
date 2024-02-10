// config.cmd -> command to run
// config.expOutput -> expected output to stdin
// config.expStatus -> expected return status
// config.logPath -> logFile to write result into (if any)
// config.errorPath -> file to log errors into

logWrongStatus(Map config = [:]) {
    writeFile (
        file: config.logPath,
        text: "Expected return status:\n${expStatus}\nBut got:\n${status}.\n"
    )
}
logWrongOutput(Map config = [:]) {
    writeFile (
        file: config.logPath,
        text: "Expected output in stdout:\n${config.expOutput}\nBut got:\n${config.stdOutput}.\n"
    )
}

//    config.logPath ->    log file to write into
//    config.cmd     ->     command that was ran
//    config.expOutput    ->    expected Output
//    config.stdOutput    ->    Output that was produced by the command
//    
//

def logKO(Map config = [:]) {
    writeFile (
        file: config.logPath,
        text: ">> ${config.cmd}:KO\n"
    )
    if ( config.outputResult == false ) {
        logWrongOutput(
            logPath = config.logPath,
            expOutput = config.expOutput,
            stdOutput = config.stdOutput
        )
    }
    if ( config.statusResult == false ) {
        logWrongStatus(
            logPath = config.logPath,
            expStatus = config.expStatus,
            status = config.status
        )
    }
    writeFile (
        file: config.logPath,
        text: "       v('_'v)\n\n"
    )
}
def logOK(Map config = [:]) {
    writeFile (
        file: config.logPath,
        text: ">> ${config.cmd}:OK\n"
    )
    writeFile (
        file: config.logPath,
        text: "(^'o')^  ^('o'^)  ^('o'^)^('o'^)\n\n"
    )
}

@NonCPS
def call(Map config = [:]) {
    def String commandToRun = config.cmd
    if ( commandToRun == null )
        return 84

    sh "echo Executing ${config.cmd}..."/////////////////////////
    def String expOutput = config.expOutput
    def String expStatus = config.expStatus
    def String logPath = config.logPath
        if ( logPath == null || logPath == "" )
            logPath = "Result.log"
    def int status = 0
    def String stdOutput = ""
    def process = null
    
    //Run command thanks to java.lang.Process (f*ck the documentation tho)
    try {
        process = commandToRun.execute()

        if ( process.isAlive() ) {
            process.waitFor()
        }
        status = process.exitValue()
        stdOutput = process.getText().trim()

    } catch (Exception e) {
        echo "!!! Exception: ${e.message}"
    }
    process.destroy()
    
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

    echo "Starting comparison and logging."
    if ( statusResult == true && outputResult == true ) {
        logOK(
            logPath = logPath,
            cmd = commandToRun
        )
    } else {
        logKO(
            logPath = logPath,
            cmd = commandToRun,
            outputResult = outputResult,
            expOutput = expOutput,
            stdOutput = stdOutput,
            statusResult = statusResult,
            expStatus = expStatus,
            status = status
        )
    }
    return 0
}
