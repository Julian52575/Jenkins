job( 'Get Jenkins Files' ) {
    environmentVariables(REPO: 'git@github.com:Julian52575/lib.git')

    scm {
        git {
            remote {
                name('Jenkins Files')
                url( ${REPO} )
            }
        }
    }
}

