def call(Map config = [:]) {
    sh "echo -n 'KO. ' >> ${config.logName}"
}

