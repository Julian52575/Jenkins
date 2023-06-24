pipeline {
  agent any
  stages {
    stage('Building') {
      steps {
        sh 'make'
      }
    }

    stage('Verifying binary') {
      steps {
        sh 'touch -x math'
      }
    }

    stage('Executing program') {
      steps {
        sh '''./math + 1 1
 echo $?'''
      }
    }

  }
}