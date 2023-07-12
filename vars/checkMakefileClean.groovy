def call(Map config = [:]) {
    sh "test Makefile"
    sh "make"
    sh "test ${config.name} "
    sh "make clean"
    sh "test ! -f ${config.name} "
}
