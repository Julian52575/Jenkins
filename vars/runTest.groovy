def call(Map config = [:]) {
    //name -> 1+1
    //cmd -> ./math 1 1 +
    //expOutput -> Your result is 2.
    //expReturnValue -> 0

    //Starting log
    sh "echo -n ${config.name}: >> new_mouli_log.txt"
    sh 'echo -n "\t\t|\t" >> new_mouli_log.txt'
    //
    //Stdout output
    def output = sh (
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
    //
    //Print result in next line
    sh "echo '\t\t|O${env.output}\t\t|S${statusCode}\t\t|' >> new_mouli_log.txt"
}
