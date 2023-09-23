def call(Map config = [:] ) {
    loadLinuxScript(name: 'my-coding.sh')
    sh "my-coding.sh . ."
    sh "test -x coding-style-reports.log"
}
