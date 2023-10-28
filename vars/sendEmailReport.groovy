def call(Map config = [:] ) {
    sh 'echo "\n\nCourtesy of everyone\'s favorite Rulian©." >> new_mouli_log.txt'
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
