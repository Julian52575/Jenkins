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
    def String logContent = ""
    def process = null

    //reading log file
    if ( fileExists(logPath) ) {
        logContent = readFile logPath
    }
    
    //Running cmd
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
    //implement 'readFile fileName' into a single variable that will be appended Then written at the end
    if ( statusResult == true && outputResult == true ) {
        logContent = logContent + ">> ${commandToRun}:OK\n" + "(^'o')^  ^('o'^)  ^('o'^)^('o'^)\n\n"

    } else {
        echo "Logging KO\n"
        logContent = logContent + ">> ${commandToRun}:KO\n"
        if ( outputResult == false ) {
            logContent = logContent + "Expected output in stdout:\n${expOutput}\nBut got:\n${stdOutput}.\n"
        }
        if ( statusResult == false ) {
            logContent = logContent + "Expected return status:\n${expStatus}\nBut got:\n${status}.\n" 
        }
        logContent = logContent + "       v('_'v)\n\n"
    }
    writeFile (
        file: logPath,
        text: logContent
    )
    return 0
}
