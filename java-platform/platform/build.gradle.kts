plugins {
    `java-platform`
    `project-report`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    // import a BOM
    api(platform("org.springframework.boot:spring-boot-dependencies:2.4.2"))

    constraints {
        api("org.apache.poi:poi:4.1.2")
    }
}
