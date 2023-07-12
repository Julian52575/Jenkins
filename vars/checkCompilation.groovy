def call(Map config = [:] ) {
    sh 'echo "Testing Compilation of ${config.name}" '
    sh 'ls'
    sh 'test Makefile'
    sh 'make'
    sh 'test -x ${config.name}'
    sh 'echo ${config.name} has been correctly compiled !'
} 
