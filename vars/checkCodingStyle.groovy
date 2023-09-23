def call(Map config = [:] ) {
    loadScript(name : ".lol.txt")
    loadScript(name: "my-coding.sh")
    sh "./my-coding.sh . ."
    sh "test -x coding-style-reports.log"
}
