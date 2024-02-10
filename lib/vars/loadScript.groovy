
    // config.name = new script name
    // config.path = original script path

//bool
def call(Map config = [:]) {
    def String fileContent = ""
    def String fileName = config.newSciptName
    def String filePath = config.filePath

    if ( fileName == null ) {
        sh "echo 'Library Warning:\t loadScript called without proper config.name, skipping...' "
        return false
    }
    if ( filePath == null )
        filePath = fileName
    
    fileContent = libraryResource filePath
    writeFile file: fileName, text: fileContent
    sh "chmod a+x ${filePath}"
    return true
}
