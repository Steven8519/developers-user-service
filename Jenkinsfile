pipeline {
  environment {
    imagename = "steven8519/developers-user-service"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }

  agent any
  stages {
    stage('Checkout') {
      steps {
        git([url: 'https://github.com/Steven8519/developers-user-service.git', branch: 'master', credentialsId: 'Github'])

      }
    }

    stage('Gradle Build') {
        steps{
           script {
              sh "./gradlew clean build"
            }
        }
    }
    stage('Gradle Test') {
        steps{
           script {
             sh "./gradlew clean test"
            }
         }
    }

    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build imagename
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push("$BUILD_NUMBER")
             dockerImage.push('latest')

          }
        }
      }
    }

    stage("Deploy To Kubernetes Cluster") {
       steps {
           sh 'kubectl apply -f deployment.yml'
        }
     }

    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $imagename:$BUILD_NUMBER"
         sh "docker rmi $imagename:latest"

      }
    }
  }
}