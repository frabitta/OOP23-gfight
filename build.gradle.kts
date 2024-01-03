plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.32.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set(/* Main class name here */)
}

repositories {
    mavenCentral()
}

dependencies {
    val junitVersion = "5.10.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging { events(TestLogEvent.values()) }
    testLogging.showStandardStreams = true
}
