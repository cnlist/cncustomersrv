pipeline {
    agent any


    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git credentialsId: '03b8c9d9-21c4-4d35-9fcc-bc297a69dc2c', url: 'https://github.com/cnlist/cncustomersrv.git'

                withMaven(maven:'maven'){
                    sh "mvn clean package"
                }
            }


        }

        stage('Deploy'){
            steps{
                script{
                    sh  'curl -T "target/customer-server-0.0.1-SNAPSHOT.war" "http://jenkins:jenkins@192.168.10.5:8012/manager/text/deploy?path=/customserv&update=true"'
                }
            }
        }
    }
}