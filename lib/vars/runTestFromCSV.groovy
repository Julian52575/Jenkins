
    //name -> 1+1
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    //logName -> New_mouli.log
    //depthName -> InDepth.log
    //CSVname -> ./NMtests.csv

def call(Map config = [:]) {
    if ( fileExists("${config.CSVname}") == false ) {
        sh "echo 'Cannot find CSV name for testing\"${config.CSVname}\".' >> ${config.logName}"
        return 0
    }
    def csvFile = readFile "${config.CSVname}"
    
    def csvLines = csvFile.readLines()

    for (def line in csvLines) {
        def fields = line.split(',')

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
}
