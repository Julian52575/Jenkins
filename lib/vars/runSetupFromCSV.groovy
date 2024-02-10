    //CSVname -> ./Setup.csv

def call(Map config = [:]) {
    if ( fileExists("${config.CSVpath}") == false ) {
        sh "echo 'Cannot find CSV name for Setup \"${config.CSVpath}\".' >> ${config.logName}"
        return 84
    }
    def csvFile = readFile "${config.CSVpath}"
    def csvLines = csvFile.readLines()

    for (def line in csvLines) {
        def fields = line.split(',')

        echo "Test from runSetupCSV"///////////////////
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
