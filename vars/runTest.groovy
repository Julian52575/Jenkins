def call(Map config = [:]) {
    //name
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    output = " "

    sh 'echo "Hello from runTest" '
    //Starting log
    sh "echo -n ${config.name}: >> new_mouli_log.txt"
    sh 'echo -n "\t\t|\t" >> new_mouli_log.txt'
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
    sh 'echo -n "\t\t|\t\t" >> new_mouli_log.txt'
    //
    //returnValue
    output = sh (
        script: "${config.cmd}",
        returnStatus: true
    )
    if ( output == config.expReturnValue ) {
        printOK()
    } else {
        printKO()
    }
    sh 'echo -n "\t|\t" >> new_mouli_log.txt'
    //
    //newline for next test
    sh "echo ${config.cmd}:\t >> new_mouli_log.txt"
}
