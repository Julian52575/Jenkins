def call(Map config = [:]) {
    hasCompiled = 0
    date = "01 January 1900"
    hour = "0:01"
    header = "\t\t${config.name}\n${date}\t\t${hour}\n\n"

    sh "echo ${header} > new_mouli_log.txt"
    hasCompiled = checkCompilation( name:"${config.name}" )
    if ( hasCompiled == 1 ) {
        exit 84
    }
    //checkMakefileClean( name:"math" )
    hasDebugSymbols = checkDebugSymbols( name:"${config.name}" )
}
