pipeline {
  agent any
  stages {
    stage('Stage 1') {
      steps {
        sh 'echo "Hello from Stage 1"'
      }
    }
    stage('Stage 1.a') {
      parallel {
        stage('Stage 1.a') {
          steps {
            echo 'Exceuting Parallel Steps using path 1'
          }
        }
        stage('Stage 1.b') {
          steps {
            echo 'Executing Stage 1.b using path 2'
          }
        }
      }
    }
    stage('Aggregate Result of Parallel Exceution') {
      steps {
        echo 'Aggregated Results'
      }
    }
    stage('Sonar Check') {
      steps {
        echo 'Performing Sonar Check'
      }
    }
    stage('Deploy to Dev Env') {
      steps {
        echo 'Deploying to env Dev'
      }
    }
    stage('End') {
      steps {
        echo 'End'
      }
    }
  }
}