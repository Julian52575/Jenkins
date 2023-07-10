
job('Compiling the maths') {

    environmentVariables(EXE: 'math')
    
    description(readFileFromWorkspace('acme-tests', 'math/JOB_DESCRIPTION.TXT'))

    steps {
        shell( 'test Makefile' )
        shell( 'make' )
        shell( 'test -x ${EXE}' )
    }
}

job( 'Testing the maths' ) {



    steps {
        shell( './math + 1 2 >> log' )
        shell( './math - 3 4 >> log' )
        shell( './math / 5 6 >> log' )
        shell( './math "*" 7 8 >> log' )
        shell( './math % 9 9 >> log' )

        shell( 'diff ./log ./math_results ' )
        shell( 'rm log' )
    }
}
