import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
//    application
    `java-library`
    `maven-publish`
    jacoco
}

group = "gov.cdc.dex"
version = "2.0.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://imagehub.cdc.gov/repository/maven-ede-group/")
    }
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")

    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("org.apache.logging.log4j:log4j-slf4j18-impl:2.18.0")
    implementation(kotlin("stdlib-jdk8"))

}

tasks.test {
    useJUnitPlatform()
    //NOTE: ENVIRONMENT BLOCK MUST STAY IN THE SAME FORMAT AS BELOW - WILL BREAK CICD PIPELINE

    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

//
//publishing {
//
//    publications {
//        create<MavenPublication>("myLibrary") {
//            from(components["java"])
//        }
//    }
//    repositories {
//        maven {
//            val releasesRepoUrl  = "https://imagehub.cdc.gov/repository/maven-ede/"
//            val snapshotsRepoUrl = "https://imagehub.cdc.gov/repository/maven-ede-snapshot/"
//            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
//            name = "nexus"
//            credentials(PasswordCredentials::class){
//                username= System.getenv("IMAGEHUB_USERNAME")
//                password= System.getenv("IMAGEHUB_PASSWORD")
//           }
//        }
//    }
//
//}

