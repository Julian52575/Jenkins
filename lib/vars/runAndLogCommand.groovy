// config.cmd -> command to run
// config.expOutput -> expected output to stdin
// config.expStatus -> expected return status
// config.logPath -> logFile to write result into (if any)
// config.errorPath -> file to log errors into

//@NonCPS //is needed ?
def call(Map config = [:]) {
    def String commandToRun = config.cmd
    if ( commandToRun == null )
        return 84

    sh "echo Executing ${config.cmd}..."/////////////////////////
    def String expOutput = config.expOutput
    def String expStatus = config.expStatus
    def String logPath = config.logPath
        if ( logPath == null )
            logPath = "Result.log"
    def int status = 0 //COmes from java stuff, no cast to sh compatible
    def String stdOutput = ""
    def process = null
    
    //Run command thanks to java.lang.Process (fuck the documentation tho)
    try {
        process = commandToRun.execute()

        def bob = process.isAlive()
        if ( bob == true ) {
            echo "${commandToRun}:\tProcess still running."
        } else {
            echo "${commandToRun}:\tProcess stoped."
        }

        process.waitFor()
        echo "${commandToRun}:\twaitFor successful."
        
        status = process.exitValue()
        echo "${commandToRun}:\tExit value successful."
        echo "Exit value:\t${status}."
        
        stdOutput = process.getText().trim()
        echo "${commandToRun}:\tText succesful."
        echo "Get Text:\t${stdOutput}."

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

    echo "Before write in ${logPath}." //////////String
    writeFile (
        file: "lol.txt",
        text: 'lol\n'
    )
    writeFile (
        file: logPath,
        text: ">> "
    )
    writeFile (
        file: logPath,
        text: "${commandToRun}:\t\t"
    )
    echo "After write" ////////////
    echo "Starting comparison and logging."
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
