
job('Compiling the maths') {

    environmentVariables(NAME: 'Julian')

    steps {
        shell( 'test Makefile' )
        shell( 'make' )
        shell( 'test -x math' )
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
