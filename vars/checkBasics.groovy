def call(Map config = [:]) {
    hasCompiled = 0
    date = "01 January 1900"
    hour = "0:01"

    sh "echo ${config.name} > new_mouli_log.txt"
    sh "echo ${config.author} >> new_mouli_log.txt"
    sh "echo ${date} >> new_mouli_log.txt"
    sh "echo ${hour} >> new_mouli_log.txt"
    sh 'echo " " >> new_mouli_log.txt'
    sh 'echo " " >> new_mouli_log.txt'

    sh 'echo "---" >> new_mouli_log.txt'
    
    sh 'echo " " >> new_mouli_log.txt'
    hasCompiled = checkCompilation( name:"${config.name}" )
    if ( hasCompiled == 1 ) {
        exit 84
    }
    //checkMakefileClean( name:"math" )
    hasDebugSymbols = checkDebugSymbols( name:"${config.name}" )
}
