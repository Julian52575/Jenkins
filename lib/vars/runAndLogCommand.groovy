// config.cmd -> command to run
// config.expOutput -> expected output to stdin
// config.expStatus -> expected return status
// config.logPath -> logFile to write result into (if any)
// config.errorPath -> file to log errors into

logWrongStatus() {
    writeFile (
        file: logPath,
        text: "Expected return status:\n${expStatus}\nBut got:\n${status}.\n"
    )
}
logWrongOutput() {
    writeFile (
        file: logPath,
        text: "Expected output in stdout:\n${expOutput}\nBut got:\n${stdOutput}.\n"
    )
}

//    logPath ->    log file to write into
//    cmd     ->     command that was ran
//    expOutput    ->    expected Output
//    stdOutput    ->    Output that was produced by the command
//    
//
logKO(String logPath, String cmd, boolean outputResult, String expOutput, String stdOutput,
                                  boolean statusResult, int expStatus, int status)             {
    echo "Logging KO\n"
    writeFile (
        file: logPath,
        text: ">> ${cmd}:KO\n"
    )
    if ( config.outputResult == false ) {
        logWrongOutput(logPath, expOutput, stdOutput)
    }
    if ( config.statusResult == false ) {
        logWrongStatus(logPath, expStatus, status)
    }
    writeFile (
        file: logPath,
        text: "       v('_'v)\n\n"
    )
}
logOK(String logPath, String cmd) {
    echo "Logging OKay\n"
    writeFile (
        file: logPath,
        text: ">> ${cmd}:OK\n"
    )
    writeFile (
        file: logPath,
        text: "(^'o')^  ^('o'^)  ^('o'^)^('o'^)\n\n"
    )
}

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
    
    try {
        def list = runCmdFromString(
            cmd = commandToRun
        )
        stdOutput = list[0]
        status = list[1]
    } catch (Exception e) {
        echo "!!! Exception: ${e.message}"
    }
    
    echo "Testing expOutput.\n"
    def boolean outputResult = false
    if ( stdOutput == expOutput ) {
        outputResult = true
    }
    echo "Testing expStatus.\n"
    def boolean statusResult = false
    if ( status == expStatus ) {
        statusResult = true
    }

    echo "Starting comparison and logging.\n"
    if ( statusResult == true && outputResult == true ) {
        runAndLogCommand.logOK(
            logPath = logPath,
            cmd = commandToRun
        )
    } else {
        runAndLogCommand.logKO(
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
