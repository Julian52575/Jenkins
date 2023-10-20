def call(Map config = [:]) {
    //name
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    def output = " "

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
    sh 'echo -n "\t\t|\t" >> new_mouli_log.txt'
    //
    //ReturnValue
    def statusCode = sh (
        script: "${config.cmd}",
        returnStatus: true
    )
    sh 'echo "Debug returnValue _${statusCode}_" '
    if ( statusCode == config.expReturnValue ) {
        printOK()
    } else {
        printKO()
    }
    //
    //Print cmd in log
    sh 'echo -n "\t\t|\t" >> new_mouli_log.txt'
    sh "echo -n ${config.cmd} >> new_mouli_log.txt"
    sh 'echo "\t|" >> new_mouli_log.txt'
}
