def call(Map config = [:]) {
    def fileContent = libraryResource "scripts/${config.name}"
    writeFile file: "${config.name}", text: fileContent
    sh "chmod a+x ./${config.name}"
}
