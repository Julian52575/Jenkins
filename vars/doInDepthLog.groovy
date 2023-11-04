def call(Map config = [:]) {
    //doInDepthLog(
    //    cmd: cmd,
    //    output: output,
    //    statusCode: statusCode
    //)
    sh "echo '>>${config.cmd}' >> InDepth.log"
    sh "echo '[[${config.output}]]' >> InDepth.log"
    sh "echo 'Return Value: ${config.statusCode}' >> InDepth.log"
    sh "echo '___' >> InDepth.log"
}
