
    //name -> 1+1
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    //logName -> New_mouli.log
    //depthName -> InDepth.log
    //CSVpath -> ./NMtests.csv

def call(Map config = [:]) {
    def csvPath = config.CSVpath
    
    if ( fileExists("${config.CSVpath}") == false ) {
        sh "echo No CSV file for testing called _${config.CSVpath}_"
        return false
    }
    def csvContent = readFile "${config.CSVname}"
    def csvLines = csvContent.readLines()

    for (def line in csvLines) {
        def fields = line.split(',')
        def commandToRun = fields[1]
        
        if (fields[1] != "" && fields[1][0] == '.' && fields[1][1] == '/') {//if cmd is a path to a script
            loadScript(
                name: "tmp.sh",
                path: field[1]
            )
            commandToRun = "bash tmp.sh"
        }
        
        if ( fields[0] != "name" && fields[1] != "cmd") { //skip csv header
            runTest(
                name: fields[0],
                cmd: fields[1],
                expOutput: fields[2],
                expReturnValue: fields[3] as Integer,
                logName: "${config.logName}",
                depthName: "${config.depthName}"
            )
        }
    }
    return true
}
