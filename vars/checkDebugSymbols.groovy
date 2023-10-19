def call(Map config = [:]) {
    hasDebugSymbol = sh (
        script: "file ${config.name} | grep -v 'with debug_info'",
        returnStatus: true
    )
    sh 'echo -n "[[[Debug symbol:\t" >> new_mouli_log.txt'
    //if ( hasDebugSymbol == "0" ) {
    //    sh 'echo -n "OK. No debbuging symbols present in binary." >> new_mouli_log.txt'    
    //} else {
    //    sh 'echo -n "KO. Binary has been compiled with debbuging info." >> new_mouli_log.txt'
    //}
    sh 'echo "\n" >> new_mouli_log.txt'
    return hasDebugSymbol
}
