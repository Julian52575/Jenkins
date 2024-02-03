
    // config.name = new script name
    // config.path = original script path

//bool
def call(Map config = [:]) {
    def fileContent = ""
    def fileName = config.name
    def filePath = config.path

    if ( config.name == "" ) {
        sh "echo "Library Warning:\t loadScript called without proper config.name, skipping..."
        return false
    }
    if ( config.path == "" )
        filePath = fileName
    
    fileContent = libraryResource filePath
    writeFile file: fileName, text: fileContent
    sh "chmod a+x ${filePath}"
    return true
}
