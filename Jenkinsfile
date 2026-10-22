
pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3.10'
    }

    stages {
        stage('Build') {
            steps {
                echo '=== BUILD: Компиляция проекта ==='
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                echo '=== TEST: Запуск тестов ==='
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo '=== PACKAGE: Создание JAR ==='
                bat 'mvn package -DskipTests'
            }
        }

        stage('Inspect Target') {
            steps {
                echo '=== INSPECT TARGET: Содержимое каталога target ==='
                bat 'if exist target (dir target) else (echo Каталог target не найден & exit /b 1)'
            }
        }
    }
}
