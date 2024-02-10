// logPath     ->    main log file path
// depthPath    ->    In-Depth log file path

def call(Map config = [:] ) {
    sh "echo '\n\nCopyright Rulian©.' >> ${config.logPath}"
    def logContent = sh (
                script: "cat ${config.logPath} || true",
                returnStdout: true
    )
    //send file to eMail
    emailext body: "${logContent}",
    subject: "[New Mouli] Logs for ${config.projectName}",
    to: config.receiverEmailAddress,
    attachmentsPattern: config.depthPath
}
