#!/usr/bin/env groovy

properties([
        parameters([
                string(name: 'gitBranch',
                        defaultValue: 'refs/heads/master',
                        description: 'The GIT branch to check out'),
        ]),
        buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '10', daysToKeepStr: '', numToKeepStr: '10')),
        disableConcurrentBuilds(),
        pipelineTriggers([pollSCM('H/5 * * * *')])
])

timestamps {
    node {
        try {
            stage('Preparation') {
                checkout([$class                           : 'GitSCM',
                          branches                         : [[name: '$gitBranch']],
                          doGenerateSubmoduleConfigurations: false,
                          extensions                       : [[$class: 'CleanBeforeCheckout']],
                          submoduleCfg                     : [],
                          userRemoteConfigs                : [[credentialsId: 'gitlab-access-id',
                                                               url          : 'https://github.com/rgannu/playground.git']]])
                pom = readMavenPom file: 'pom.xml'
                echo "VERSION:${pom.version}"
            }

            stage('Build') {
                withEnv(["VERSION=${pom.version}", 'MAVEN_HOME=/ap/nmhn/java/apache-maven-3.3.9', 'JAVA_HOME=/var/lib/jenkins/tools/hudson.model.JDK/jdk8', 'PATH+EXTRA=$MAVEN_HOME/bin:$JAVA_HOME/bin:/bin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin']) {
                    sh 'echo $MAVEN_HOME'
                    mvn 'clean install -U -B -e'
                }
            }


            stage('Publish Media') {
                withEnv(['MAVEN_HOME=/ap/nmhn/java/apache-maven-3.3.9', 'JAVA_HOME=/var/lib/jenkins/tools/hudson.model.JDK/jdk8', 'PATH+EXTRA=$MAVEN_HOME/bin:$JAVA_HOME/bin:/bin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin']) {
                    withCredentials([[$class          : 'UsernamePasswordMultiBinding', credentialsId: 'artifactory-user',
                                      passwordVariable: 'artifactoryPassword',
                                      usernameVariable: 'artifactoryUser']]) {
                        mvn 'deploy -P artifactory -Dartifactory.username=$artifactoryUser -Dartifactory.password=$artifactoryPassword'
                    }
                }
            }

            currentBuild.result = 'SUCCESS'
        }
        catch (Exception err) {
            currentBuild.result = 'FAILURE'
            echo "${err}"
        }
        finally {
            stage('Notify Build Status') {
                echo "${BUILD_URL}"
                echo "Playground: Build # ${BUILD_NUMBER} - Build is finished. Result: ${currentBuild.result}"
                emailext body: "Playground: Build # ${BUILD_NUMBER} - ${currentBuild.result}: " +
                        "\n\n" +
                        "Check console output at ${BUILD_URL} to view the results.",
                        recipientProviders: [[$class: 'CulpritsRecipientProvider'],
                                             [$class: 'RequesterRecipientProvider'],
                                             [$class: 'DevelopersRecipientProvider'],
                                             [$class: 'FirstFailingBuildSuspectsRecipientProvider'],
                                             [$class: 'FailingTestSuspectsRecipientProvider']],
                        replyTo: 'Ganesh.Ramasubramanian@nokia.com',
                        subject: "Playground: Build # ${BUILD_NUMBER} - ${currentBuild.result} !",
                        to: 'Ganesh.Ramasubramanian@nokia.com'
            }
        }
    }
}

def mvn(args) {
    sh "${tool 'maven339'}/bin/mvn ${args}"
}
