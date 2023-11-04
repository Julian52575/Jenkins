def call(Map config = [:]) {
    hasCompiled = 0

    //Prints Header
    sh "echo ${config.name} > ${config.logName}"
    sh "echo ${config.author} >> ${config.logName}"
    sh "date '+%A %d %B - %H:%M' >> ${config.logName}"
    sh "echo '---' >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
    //
    hasCompiled = checkCompilation( 
                    name:"${config.name}",
                    logName: "${config.logName}"
                  )
    if ( hasCompiled == 1 ) {
        exit 84
    }
    //checkMakefileClean( name:"math" )
    hasDebugSymbols = checkDebugSymbols(
                        name:"${config.name}",
                        logName: "${config.logName}"
                      )
    sh "echo ' ' >> ${config.logName}"
    return hasCompiled
}
