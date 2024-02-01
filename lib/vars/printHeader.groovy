def call(Map config = [:]) {
    hasCompiled = 0
    author = currentBuild.getBuildCauses()[0].shortDescription + " / " + currentBuild.getBuildCauses()[0].userId
    date = date '+%A %d %B - %H:%M'
    merge = config.name + '|' + author + '|' + date + '|' 
    strlen = echo -n merge | wc -c

    //Prints Header
    sh "echo '${config.name}|${author}|${date}|' > ${config.logName}"
    sh "printf '%0.s-' {1..${strlen}} >> ${config.logName}"
    sh "echo ' ' >> ${config.logName}"
}
