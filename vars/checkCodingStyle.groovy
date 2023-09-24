def call(Map config = [:] ) {
    loadScript(name: a.sh)
    loadScript(name: "my-coding.sh")
    sh "./my-coding.sh . ."
    sh "test -x coding-style-reports.log"
}
//readFile
