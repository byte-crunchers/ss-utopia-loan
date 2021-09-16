pipeline {
    agent any

    stages {
      stage('checkout') {
        steps {
          git branch: 'feature_jenkins', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-loan.git'
        }
      }
      stage("Clean install") {
            
            steps {
              
                  sh 'mvn clean install'
              
            }
          }
        
        stage("SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('SonarQube') {
                  sh 'mvn sonar:sonar'
              }
            }
          }
          stage("Quality Gate") {
            steps {
              echo message: "can not do on local machine "
             /* timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }*/
            }
          }
          stage('Build') {
            steps {
                    sh 'docker build . -t ss-utopia-loan:latest'
            }
        }
        stage('Deploy') {
            steps {
                //sh 'docker push jbnilles/ss-utopia-loan:latest'
                script {
                  docker.withRegistry('https://22288715120.dkr.ecr.us-east-2.amazonaws.com/ss-utopia-loan',
                  'ecr:us-east-2:ss-AWS') {
                    docker.image('ss-utopia-loan').push('latest')
                  }
                }
            }
        }
    }

}