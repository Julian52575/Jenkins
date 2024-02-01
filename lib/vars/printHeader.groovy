def call(Map config = [:]) {
    hasCompiled = 0
    author = currentBuild.getBuildCauses()[0].shortDescription + " / " + currentBuild.getBuildCauses()[0].userId
    currentTime = sh (
        script: "date '+%A %d %B - %H:%M' | tr -d '\n'",
        returnStdout: true
    )
    mergedText = config.name + ' | ' + author + ' | ' + currentTime + ' | '
    strlen = sh (
        script: 'echo -n "${mergedText}" | wc -c | tr -d "\n" ',
        returnStdout: true
    )
    chinaWall = sh " printf %100s |tr ' ' '=' "

    //Prints Header
    sh "echo '${mergedText}' > ${config.logName}"
    sh "echo '${chinaWall}' >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
}
