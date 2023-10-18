def call(Map config = [:]) {
    areDebugSymbol = sh (
        script: "file ${config.name} | grep -q -v 'with debug_info'",
        returnStatus: true
    )
    return areDebugSymbol
}
