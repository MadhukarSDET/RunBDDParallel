pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build & Test') {
      steps {
        sh 'mvn clean test -Dtags=@Smoke'
      }
    }
    stage('Publish Report') {
      steps {
        publishHTML (target: [
          allowMissing: false,
          alwaysLinkToLastBuild: true,
          keepAll: true,
          reportDir: 'target/extent-report',
          reportFiles: 'extent.html',
          reportName: 'Extent Report'
        ])
      }
    }
  }
}
