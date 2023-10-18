def call(Map config = [:]) {
    sh "file ${config.name} | grep -q -v 'with debug_info'"
    return $?    
}
