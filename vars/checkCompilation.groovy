def call(Map config = [:] ) {
    sh 'test Makefile'
    sh 'make'
    sh 'test -x ${config.binary_name}'
    sh 'echo ${config.binary_name} has been correctly compiled !'
} 
