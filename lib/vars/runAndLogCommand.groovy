// config.cmd -> command to run
// config.expOutput -> expected output to stdin
// config.expStatus -> expected return status
// config.logPath -> logFile to write result into (if any)
// config.errorPath -> file to log errors into

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
        echo "Logging OKay\n"
        writeFile (
            file: logPath,
            text: ">> ${commandToRun}:OK\n"
        )
        writeFile (
            file: logPath,
            text: "(^'o')^  ^('o'^)  ^('o'^)^('o'^)\n\n"
        )
        
    } else {
        echo "Logging KO\n"
        writeFile (
            file: logPath,
            text: ">> ${commandToRun}:KO\n"
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
        writeFile (
            file: logPath,
            text: "       v('_'v)\n\n"
        )
    }
    return 0
}
