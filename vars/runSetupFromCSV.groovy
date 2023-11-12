    //CSVname -> ./Setup.csv

def call(Map config = [:]) {
    if ( fileExists("${config.CSVname}") == false ) {
        return 1
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
}
