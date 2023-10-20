def call() {
    message = 'OK. '
    sh (
        script: 'echo -n "${message}" >> new_mouli_log.txt'
    )
}

