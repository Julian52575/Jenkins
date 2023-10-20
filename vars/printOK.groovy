def call() {
    message = '\033[0;32mOK\033[0m. '
    sh (
        script: 'echo -n "${message}" >> new_mouli_log.txt'
    )
}

