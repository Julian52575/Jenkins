def call(Map config = [:]) {
    def int hasCompiled = 0
    def String mergedText = ""
    def String chinaWall = ""
    def String strlen = ""
    def String author = currentBuild.getBuildCauses()[0].shortDescription + " / " + currentBuild.getBuildCauses()[0].userId
    def String currentTime = sh (
        script: "date '+%A %d %B - %H:%M' | tr -d '\n'",
        returnStdout: true
    )
    mergedText = config.name + ' | ' + author + ' | ' + currentTime + ' | '
    strlen = sh (
                script: 'echo -n "${mergedText}" | wc -c',
                returnStdout: true
            ).trim("\n")
    chinaWall = sh (
                    script: " printf %${strlen}s |tr ' ' '-' ",
                    returnStdout: true
                )

    //Prints Header
    sh "echo '${mergedText}' > ${config.logName}"
    sh "echo '${chinaWall}' >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
}
