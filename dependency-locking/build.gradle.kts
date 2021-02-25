plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:LATEST_RELEASE"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}
