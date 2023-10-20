def call(Map config = [:]) {
    hasCompiled = 0

    //Prints Header
    sh "echo ${config.name} > new_mouli_log.txt"
    sh "echo ${config.author} >> new_mouli_log.txt"
    sh 'date "+%A %d %B - %H:%M" >> new_mouli_log.txt'
    sh 'echo "---" >> new_mouli_log.txt'
    //
    hasCompiled = checkCompilation( name:"${config.name}" )
    if ( hasCompiled == 1 ) {
        exit 84
    }
    //checkMakefileClean( name:"math" )
    hasDebugSymbols = checkDebugSymbols( name:"${config.name}" )
}
