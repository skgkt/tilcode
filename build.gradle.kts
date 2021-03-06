plugins {
    id("com.diffplug.spotless") version "5.14.0"
}

val unsetSpotless = project.hasProperty("unsetSpotless")
if (unsetSpotless) {
    logger.lifecycle("spotlessApplyタスクを回避します。")
}

val utf8 = java.nio.charset.StandardCharsets.UTF_8.name()

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.diffplug.spotless")

    tasks.withType(Test::class).configureEach {
        useJUnitPlatform()
    }

    tasks.withType(JavaCompile::class).configureEach {
        options.encoding = utf8

        if (!unsetSpotless) {
            dependsOn(tasks.named("spotlessJavaApply"))
        }
    }

    tasks.withType(org.gradle.api.tasks.javadoc.Javadoc::class).configureEach {
        options.encoding = utf8
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            // google-java-format は 2 スペースインデント
            // AOSP は 4 スペースインデント
            googleJavaFormat("1.9").aosp()
        }
    }
}
