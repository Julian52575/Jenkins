def call(Map config = [:]) {
    hasDebugSymbol = sh (
        script: "file ${config.name} | grep -v 'with debug_info'",
        returnStatus: true
    )
    return hasDebugSymbol
}
