def call(Map config = [:] ) {
    logContent = sh (
                script: 'cat new_mouli_log.txt',
                returnStdout: true
    )

    //send file to eMail
    emailext body: "${logContent}",
    subject: "[New Mouli] Logs for ${config.projectName}",
    to: params.Email,
    attachmentsPattern: 'new_mouli_log.txt'
}
