def call(Map config = [:]) {
    hasCompiled = 0

    hasCompiled = checkCompilation( 
                    name:"${config.name}",
                    logName: "${config.logName}",
                    depthName: "${config.depthName}"
                  )
    if ( hasCompiled == 84 ) {
        return 84
    }
    //checkMakefileClean( name:"math" )
    hasDebugSymbols = checkDebugSymbols(
                        name:"${config.name}",
                        logName: "${config.logName}"
                      )
    sh "echo ' ' >> ${config.logName}"
    return 0
}
