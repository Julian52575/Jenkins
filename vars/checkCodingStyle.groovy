def call(Map config = [:] ) {
    loadScript(name: "./a.sh")
    sh "./a.sh"
    loadScript(name: "scripts/my-coding.sh")
    sh "./my-coding.sh . ."
    sh "test -x coding-style-reports.log"
}
//readFile
