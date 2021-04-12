plugins {
    id("org.liquibase.gradle") version "2.0.4"
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.3.1")
    liquibaseRuntime("org.postgresql:postgresql:42.2.18")
}

liquibase {
    activities.register("test") {
        this.arguments = mapOf(
                "changeLogFile" to "migrate/changelog.xml",
                "url" to project.properties["dbUrl"],
                "username" to project.properties["dbUsername"],
                "password" to project.properties["dbPassword"]
        )
    }
}
