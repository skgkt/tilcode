plugins {
    application
    id("com.diffplug.spotless") version "5.11.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:29.0-jre")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

application {
    mainClass.set("spotless.App")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        // AOSP style
        googleJavaFormat("1.9").aosp()
    }
}

tasks.named("compileJava") {
    dependsOn("spotlessJavaApply")
}
