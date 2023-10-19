def call(Map config = [:]) {
    hasDebugSymbol = sh (
        script: "file ${config.name} | grep -v 'with debug_info'",
        returnStatus: true
    )
    //Logging
    sh 'echo -n "[[[Debug symbol:\t" >> new_mouli_log.txt'
    if ( hasDebugSymbol == "0" ) {
        printOK()
        sh 'echo -n "No debbuging symbols present in binary." >> new_mouli_log.txt'    
    } else {
        printKO()
        sh 'echo -n "Binary has been compiled with debbuging info." >> new_mouli_log.txt'
    }
    sh 'echo "\n" >> new_mouli_log.txt'
    //
    return hasDebugSymbol
}
