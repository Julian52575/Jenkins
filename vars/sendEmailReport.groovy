def call(Map config = [:] ) {
    sh "echo '\n\nCopyright Rulian©.' >> ${config.logName}"
    def logContent = sh (
                script: "cat ${config.logName} || true",
                returnStdout: true
    )
    //send file to eMail
    emailext body: "${logContent}",
    subject: "[New Mouli] Logs for ${config.projectName}",
    to: params.Email,
    attachmentsPattern: "${config.depthName}"
}
