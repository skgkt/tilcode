plugins {
    java
    id("org.springframework.boot")
}

dependencies {
    implementation(enforcedPlatform(project(":platform")))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.apache.poi:poi")
}
