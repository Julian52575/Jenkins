def call(Map config = [:]) {
    //name -> 1+1
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    def csvFilePath = "./JenkinsNewMouli.csv"
    if ( fileExists(csvFilePath) == false ) {
        return 84
    }

    def csvFile = readFile csvFilePath
    
    def csvLines = csvFile.readLines()

    for (def line in csvLines) {
        def fields = line.split(',')

        runTest(
            name: fields[0],
            cmd: fields[1],
            expOutput: fields[2],
            expReturnValue: fields[3] as Integer
        )
    }
}
