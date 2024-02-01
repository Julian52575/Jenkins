    //CSVname -> ./Setup.csv

def call(Map config = [:]) {
    if ( fileExists("${config.CSVname}") == false ) {
        sh "echo 'Cannot find CSV name for Setup \"${config.CSVname}\".' >> ${config.logName}"
        return 84
    }
    def csvFile = readFile "${config.CSVname}"
    def csvLines = csvFile.readLines()

    for (def line in csvLines) {
        def fields = line.split(',')

        if ( fields[0] != "cmd" ) { //skip CSV header
            sh (
                script: "${fields[0]}",
                returnStatus: false,
                returnStdout: false
            )
        }
    }
    return 0
}
