plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.36.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    // Define the main class for the application.
    mainClass.set("gfight.App")
}
repositories {
    mavenCentral()
}

dependencies {
    val junitVersion = "5.10.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("org.locationtech.jts:jts-core:1.19.0")
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.3")
}

tasks.test {
    useJUnitPlatform()
    testLogging { 
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true 
        }
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}
