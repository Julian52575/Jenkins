def call(Map config = [:]) {
    hasCleaned = sh (
        script: "test Makefile"
        returnStatus: true
    )
    
    sh "make"
    sh "test ${config.name} "
    sh "make clean"
    sh "test ! -f ${config.name} "
    sh "echo 'Make clean removed ${config.name} properly !' "
}
