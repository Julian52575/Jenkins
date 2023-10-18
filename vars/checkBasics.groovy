def call(Map config = [:]) {
    hasCompiled = 0
    hasDebugSymbols = 0

    sh 'echo " " > new_mouli_log.txt'
    hasCompiled = checkCompilation( name:"math" )
    if ( hasCompiled == 1 ) {
        exit 84
    }
    //checkMakefileClean( name:"math" )
    hasDebugSymbols = checkDebugSymbols( name:"math" )
}
