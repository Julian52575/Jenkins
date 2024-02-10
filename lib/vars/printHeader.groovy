def call(Map config = [:]) {
    def String logPath = config.logPath
    if (logPath == null)
        logPath = "Result.log"
    def String mergedText = ""
    def String chinaWall = ""
    def String strlen = ""
    def String author = currentBuild.getBuildCauses()[0].shortDescription + " / " + currentBuild.getBuildCauses()[0].userId
    def String currentTime = sh (
        script: "date '+%A %d %B - %H:%M' | tr -d '\n'",
        returnStdout: true
    )
    mergedText = "| " + config.name + " | " + author + " | " + currentTime + " |"
    strlen = sh (
                script: "echo -n '${mergedText}' | wc -c",
                returnStdout: true
            ).trim()
    echo "mergedText: _${mergedText}_. Strlen: _${strlen}_"
    chinaWall = "@" + sh (
                    script: " printf %${strlen}s | tr ' ' '-' ",
                    returnStdout: true
                ) + "@"

    //Prints Header
    sh "echo '${chinaWall}' >> ${logPath}"
    sh "echo '${mergedText}' >> ${logPath}"
    sh "echo '${chinaWall}' >> ${logPath}"
}
