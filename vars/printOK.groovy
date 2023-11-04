def call(Map config = [:]) {
    sh "echo -n 'OK. ' >> ${config.logName}"
}

