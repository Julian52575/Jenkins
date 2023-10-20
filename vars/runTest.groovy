def call(Map config = [:]) {
    //name
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0
    output = " "

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
    output = sh (
        script: "${config.cmd}",
        returnStatus: true
    )
    sh 'echo "Debug returnValue ${output}" '
    if ( output == config.expReturnValue ) {
        printOK()
    } else {
        printKO()
    }
    //
    //Print cmd in log
    sh 'echo -n "\t\t|\t" >> new_mouli_log.txt'
    sh "echo ${config.cmd} >> new_mouli_log.txt"
    sh 'echo "\t|" >> new_mouli_log.txt'
}
