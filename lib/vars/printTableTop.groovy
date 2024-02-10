def call(Map config = [:]) {
    def String logPath = config.logName
    if (logPath == null)
        logPath = "Result.log"

    sh "echo '-----------------------------------------------------------------------------------------' >> ${logPath}"
    sh "echo 'Test Name\t|\tStdOutput\t|\tReturnValue\t|\tCommand\t\t|' >> ${logPath}"
}

