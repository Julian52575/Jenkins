def call(Map config = [:]) {
    sh "echo '-----------------------------------------------------------------------------------------' >> ${config.logName}"
    sh "echo 'Test Name\t|\tStdOutput\t|\tReturnValue\t|\tCommand\t\t|' >> ${config.logName}"
}

