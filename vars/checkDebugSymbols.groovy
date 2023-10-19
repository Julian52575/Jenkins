def call(Map config = [:]) {
    hasDebugSymbol = sh (
        script: "file ${config.name} | grep -v 'with debug_info'",
        returnStatus: true
    )
    sh 'echo -n "[[[Debug symbol:\t'
    if ( hasDebugSymbol == "0" ) {
        sh 'echo "OK. No debbuging symbols present in binary."'    
    } else {
        sh 'echo "KO. Binary has been compiled with debbuging info."'    
    }
    return hasDebugSymbol
}
