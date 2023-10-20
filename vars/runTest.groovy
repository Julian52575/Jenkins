def call(Map config = [:]) {
    //name
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    output = " "

    sh 'echo "Hello from runTest"'
    //Starting log
    if ( config.name != null ) {
        sh "echo -n \t${config.name}:\t >> new_mouli_log.txt"
    } else {
        sh "echo -n \t${config.cmd}:\t >> new_mouli_log.txt"
    }
    //
    //Stdout output
    output = sh (
        script: "${config.cmd} || true",
        returnStdout: true
    )
    output = output.trim()

    if ( output == config.expOutput ) {
        printOK()
    } else {
        printKO()
    }
    sh "echo -n \t >> new_mouli_log.txt"
    //
    //returnValue
    if ( config.expReturnValue != null ) {
        output = sh (
            script: "${config.cmd}",
            returnStatus: true
        )
        if ( output == config.expReturnValue ) {
            printOK()
        } else {
            printKO()
        }
    }
    //
    //newline for next test
    sh "echo -n \n >> new_mouli_log.txt"
}
