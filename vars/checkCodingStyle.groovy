def call(Map config = [:] ) {
    sh "my-coding.sh . ."
    sh "test -x coding-style-reports.log"
}
