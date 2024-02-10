
    //name -> 1+1
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    //logName -> New_mouli.log
    //depthName -> InDepth.log
    //CSVpath -> ./NMtests.csv

def call(Map config = [:]) {
    def logPath = config.logPath
    if (logPath == null) {
        logPath = "Result.log"
    }
    
    def csvPath = config.CSVpath
    if ( fileExists(csvPath) == false ) {
        sh 'echo "runTestFromCsv:\tException: No file named ${csvPath}." > ${logPath}'
        return false
    }
    def csvContent = readFile "${config.CSVname}"
    def csvLines = csvContent.readLines()

    for (def line in csvLines) {
        def fields = line.split(',')
        //Fail safe for too small CSV line
        if (fields.size() < 4)
            continue
        def commandToRun = fields[1]
        
        if (fields[1] != "" && fields[1][0] == '.' && fields[1][1] == '/') {//if cmd is a path to a script
            loadScript(
                name: "tmp.sh",
                path: field[1]
            )
            commandToRun = "bash tmp.sh"
        }
        
        if ( fields[0] != "name" && fields[1] != "cmd") { //skip csv header
            runAndLogCommand.groovy(
                cmd: commandToRun,
                expOutput: fields[2],
                expReturnValue: fields[3] as Integer,
                logPath: logPath,
            )
        }
        if (commandToRun == "bash tmp.sh") {
            sh 'rm tmp.sh'
        }
        return true
    }
    return true
}
