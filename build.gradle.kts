subprojects {
    apply(plugin = "java")

    group = "bg.softuni"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        "testImplementation"("junit:junit:4.13.2")
        "testImplementation"("org.openjdk.jmh:jmh-core:1.37")
        "testAnnotationProcessor"("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release = 17
        options.encoding = "UTF-8"
    }

    tasks.withType<Test>().configureEach {
        testLogging.events("failed")
        testLogging.exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
