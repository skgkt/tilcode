plugins {
    `java-library`
    id("org.owasp.dependencycheck") version "6.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.struts:struts2-core:2.5.16")
}
