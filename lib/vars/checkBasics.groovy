def call(Map config = [:]) {
    hasCompiled = 0
    author = currentBuild.getBuildCauses()[0].shortDescription + " / " + currentBuild.getBuildCauses()[0].userId
    
    //Prints Header
    sh "echo ${config.name} > ${config.logName}"
    //sh "echo ${config.author} >> ${config.logName}"
    sh "echo ${author} >> ${config.logName}"
    sh "date '+%A %d %B - %H:%M' >> ${config.logName}"
    sh "echo '---' >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
    //
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
