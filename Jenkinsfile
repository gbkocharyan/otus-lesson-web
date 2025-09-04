import groovy.json.JsonSlurper
import hudson.triggers.SCMTrigger

node('maven_gev') {

    properties([
        parameters([
            choice(
                name: 'BROWSER',
                choices: ['chrome', 'firefox'],
                description: 'Choose browser for the tests'
            ),
            gitParameter(
                name: 'BRANCH',
                branch: '',
                branchFilter: 'origin/(.*)',
                defaultValue: 'otus-web-spring',
                description: 'Select a Git branch to build',
                quickFilterEnabled: true,
                selectedValue: 'DEFAULT',
                sortMode: 'DESCENDING',
                type: 'PT_BRANCH'
            )
        ]),
        pipelineTriggers([
            [$class: 'SCMTrigger', scmpoll_spec: '* * * * *'],
            [$class: 'TimerTrigger', spec: '0 21 * * *']
        ])
    ])

    try {
        stage('Check Allure CLI') {
            sh 'allure --version'
        }

        stage('Checkout') {
            checkout([
                $class: 'GitSCM',
                branches: [[ name: "${params.BRANCH}" ]],
                userRemoteConfigs: [[ url: 'https://github.com/gbkocharyan/otus-lesson-web.git' ]]
            ])
        }

        stage('Run Web Tests') {
            // clean old allure results
            sh "rm -rf ${WORKSPACE}/allure-results/* ${WORKSPACE}/allure-report/* || true"
            sh "mkdir -p ${WORKSPACE}/allure-results ${WORKSPACE}/allure-report"

            sh """
                docker run --name web \
                    -v ${WORKSPACE}/allure-results:/app/allure-results \
                    -v ${WORKSPACE}/allure-report:/app/allure-report \
                    localhost:5005/web_ui || true
            """

            // Copy results from container
            sh "docker cp web:/app/allure-results ${WORKSPACE}/ || true"
            archiveArtifacts artifacts: 'allure-results/**', fingerprint: true
            sh "docker rm -f web || true"
        }

    } finally {
        stage('Publish Allure & Notify') {
                allure([
                    includeProperties: false,
                    reportBuildPolicy: 'ALWAYS',
                    results: [[ path: "${WORKSPACE}/allure-results" ]]
                ])

                try {
                    def summaryFile = readFile("${WORKSPACE}/allure-report/widgets/summary.json")
                    def summary = new JsonSlurper().parseText(summaryFile)

                    def total = summary.statistic.total ?: 0
                    def passed = summary.statistic.passed ?: 0
                    def message = "💻 UI Test Execution Finished\n" +
                                  "✅ Passed: ${passed}/${total}\n" +
                                  "📊 Allure Report: ${env.BUILD_URL}allure"
                    sh """
                       curl -s -X POST https://api.telegram.org/bot7929159352:AAHz-nqnWNu1Qar1_xxnMGcUYGz2AwuQDNw/sendMessage \
                       -d chat_id=1948410043 \
                       -d text="${message}"
                    """
                } catch (Exception e) {
                    // ignore
                }
        }
    }
}
