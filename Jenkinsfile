pipeline {
	agent any
	environment {
        ENVIRON= "${GIT_BRANCH}_env"	
        DOCKER_REPO_DEV="xcoderx09/mstest-dev"
        DOCKER_REPO_TEST="xcoderx09/mstest-test"
        DOCKER_REPO_PROD="xcoderx09/mstest-prod"
	}
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    triggers {
        cron(env.BRANCH_NAME == 'dev' ? '0 * * * *' : env.BRANCH_NAME == 'test' ? '0 * * * *' : env.BRANCH_NAME == 'prod' ? '0 * * * *' : '')
    }
    stages {
        stage ('Checkout') {
            steps {
                echo "Checking out WS ${env.BRANCH_NAME} on ${env.GIT_URL} for the environment $ENVIRON"
                checkout scm
            }
        
        }
        stage ('Maven Build') {
            steps {
                withMaven {
                  bat "mvn clean package"
                }
            }
        
        }
        stage ('Docker Build') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'dev') {
                        bat "docker build --file Dockerfile -t ${env.DOCKER_REPO_DEV}:${env.BUILD_NUMBER} ."
                    }
                    if (env.BRANCH_NAME == 'test') {
                        bat "docker build --file Dockerfile -t ${env.DOCKER_REPO_TEST}:${env.BUILD_NUMBER} ."
                    }
                    if (env.BRANCH_NAME == 'prod') {
                        bat "docker build --file Dockerfile -t ${env.DOCKER_REPO_PROD}:${env.BUILD_NUMBER} ."
                    }
                }                
            }
        
        }
        stage ('Docker Publish') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'dev') {
                        withDockerRegistry([ credentialsId: "DOCKERHUB", url: "" ]) {
                           bat "docker push ${env.DOCKER_REPO_DEV}:${env.BUILD_NUMBER}"
                        }
                    }
                    if (env.BRANCH_NAME == 'test') {
                        withDockerRegistry([ credentialsId: "DOCKERHUB", url: "" ]) {
                           bat "docker push ${env.DOCKER_REPO_TEST}:${env.BUILD_NUMBER}"
                        }
                    }
                    if (env.BRANCH_NAME == 'prod') {
                        withDockerRegistry([ credentialsId: "DOCKERHUB", url: "" ]) {
                           bat "docker push ${env.DOCKER_REPO_PROD}:${env.BUILD_NUMBER}"
                        }
                    }
                }               
            }
        
        }
        stage ('Cleanup') {
            steps {
                script {
                   bat "docker rmi ${env.DOCKER_REPO_PROD}:${env.BUILD_NUMBER} --force" 
                }               
            }
        
        }
    
    }
}