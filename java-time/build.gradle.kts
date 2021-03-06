plugins {
    `java-library`
}

dependencies {
    // BOM
    api(platform("ch.qos.logback:logback-parent:1.2.3"))
    api(platform("org.junit:junit-bom:5.7.1"))
    // Lombok
    val lombokVersion = "1.18.18"
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    // Logging API
    implementation("org.slf4j:slf4j-api")
    // Test
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("ch.qos.logback:logback-classic")
}
