//    config.cmd    ->    command to run as a string
@NonCPS
def call(Map config = [:]) {
    def String stdOutput = ""
    def int status = 0
    def process = null

    if ( config.cmd == null )
        return ["", 0]
    try {
        //Run command thanks to java.lang.Process (f*ck the documentation tho)
        process = config.cmd.execute()
        if ( process.isAlive() ) {
            process.waitFor()
        }
        status = process.exitValue()
        stdOutput = process.getText().trim()
        //process.destroy()
    } catch (Exception e) {
        throw e
    }
    return [stdOutput, status]
}
//returns a list made of stdOutput and status code
