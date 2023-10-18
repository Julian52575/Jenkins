def call(Map config = [:]) {
    sh 'ls'
    hasDebugSymbol = sh (
        script: "file ${config.name} | grep -q -v 'with debug_info'",
        returnStatus: true
    )
    return hasDebugSymbol
}
