#!groovy

node {
    def projName = 'HelpMeToFood-user-services'

    deleteDir()

    def gradle = "./gradlew"
    def repoUrl = 'git@github.com:compliancesoftware/HelpMeToFood-user-services.git'
    def branch = '*/' + "${env.BRANCH_NAME_USER_SERVICE}"

    def credentials = 'd0d1c605-a0e9-4b83-be59-a5a1c45da8be'

    try {
        //sendMail('Delivery iniciado ${projName}.', 'Delivery ${projName} TAG:${versionIncremented}, iniciado.')

        stage(name: "Clone", concurrency: 1)
        echo 'Clonando repositorio...'
        checkout([$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: credentials, url: repoUrl]]])
        echo 'Repositorio clonado.'
        sh "ls"

        def gradleProperties = readFile(pwd() + "/gradle.properties")
        def splittedVersion = gradleProperties.split("=")
        def version = splittedVersion[1].trim()

        echo 'Versão encontrada: ' + version

        def versionDescription = version.tokenize(".")

        echo 'Major: ' + versionDescription[0]
        echo 'Minor: ' + versionDescription[1]
        echo 'Patch: ' + versionDescription[2]

        stage(name: "Clean", concurrency: 1)
        echo 'Limpando o ambiente...'
        sh "${gradle} clean"
        echo 'Ambiente limpo.'

        stage(name: "Build", concurrency: 1)
        echo 'Realizando build do projeto...'
        //sh "${gradle} build -x test --stacktrace"
        sh "${gradle} assemble --stacktrace"
        echo 'Build concluido com exito.'

        stage(name: "Release", concurrency: 1)
        echo 'Definindo variáveis para release...'

        def versionToIncrement = '' + "${env.VERSION_USER_SERVICE}"
        echo 'Incrementando ' + versionToIncrement

        def versionIncremented = version

        if(versionToIncrement == "patch") {
            versionIncremented = versionDescription[0] + '.' + versionDescription[1] + '.' + (versionDescription[2].toInteger() + 1)
        }
        if(versionToIncrement == "minor") {
            versionIncremented = versionDescription[0] + '.' + (versionDescription[1].toInteger() + 1) + '.' + versionDescription[2]
        }
        if(versionToIncrement == "major") {
            versionIncremented = (versionDescription[0].toInteger() + 1) + '.' + versionDescription[1] + '.' + versionDescription[2]
        }

        echo 'Realizando release para a versão: ' + versionIncremented

        sh "${gradle} release -DPrelease.useAutomaticVersion=true -DPrelease.releaseVersion=${version} -DPrelease.newVersion=${versionIncremented} --stacktrace"

        echo 'Release bem sucedida.'

        //stage(name: "Test", concurrency: 1)
        //try {
        //   sh "${gradle} test --stacktrace "
        //}
        //finally {
        //   step([$class: "JUnitResultArchiver", testResults: "**/build/test-results/test/TEST-*.xml"])
        //}

        //sendMail('Delivery finalizado com Sucesso.', 'Delivery ${projName} TAG:${tagRelease}, entregue em Produção com sucesso.')

    } catch (org.jenkinsci.plugins.workflow.steps.FlowInterruptedException fie) {
        throw fie
    } catch (Exception e) {
        throw e
    }
}

def sendMail(subject, message) {
    sendMsgToEmail('root', subject, message)
}


def sendMsgToEmail(to, subject, message) {
     mail(
            from: "root",
            to: to,
            replyTo: "root",
            subject: subject,
            body: message
    )
}