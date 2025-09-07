pipeline {
    agent any

    tools {
        maven 'Maven3'  // Jenkins configured Maven installation
        jdk 'JDK17'     // Jenkins configured JDK 17
    }

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Jenkins credentials ID
        DOCKER_IMAGE = "springbootpractice/springbootapplication"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/springbootapplication.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                sh 'echo $DOCKER_HUB_CREDENTIALS_PSW | docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh 'docker push $DOCKER_IMAGE:latest'
            }
        }
    }
}
