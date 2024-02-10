
    //name -> 1+1
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    //logName -> New_mouli.log
    //depthName -> InDepth.log
    //CSVpath -> ./NMtests.csv

def call(Map config = [:]) {
    def String logPath = config.logPath
    if (logPath == null) {
        logPath = "Result.log"
    }
    
    def String csvPath = config.CSVpath
    if ( fileExists(csvPath) == false ) {
        sh 'echo "runTestFromCsv:\tException: No file named ${csvPath}." > ${logPath}'
        return false
    }
    def String csvContent = readFile csvPath
    def csvLines = csvContent.readLines()
    def String commandToRun = ""
    
    echo "\tBefore for loop with _${csvPath}_"///////////////////////////////
    for (def line in csvLines) {
        def fields = line.split(',')
        //Fail safe for too small CSV line
        if (fields.size() < 3) {
            echo "Looping back due to size _${fields.size()}_ < 3"
            continue
        }
        commandToRun = fields[1]
        if (commandToRun == "")
            continue
        echo "\tRunning ${commandToRun}"//////////////////////////////////////
        //if CommandToRun is a path to a bash script (starts with ./)
        if (commandToRun.contains("bash") == true) {
            loadScript(
                newSciptName: "tmp.sh",
                filePath: fields[1]
            )
            commandToRun = "bash tmp.sh"
        }
        
        if ( fields[0] != "name" && fields[1] != "cmd") { //skip csv header
            runAndLogCommand(
                cmd: commandToRun,
                expOutput: fields[2],
                expReturnValue: fields[3] as Integer,
                logPath: logPath,
            )
        }
        if (commandToRun == "bash tmp.sh") {
            sh 'rm tmp.sh'
        }
    }
    return true
}
