plugins {
    id("com.diffplug.spotless") version "5.11.1"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.diffplug.spotless")

    tasks.withType(Test::class).configureEach {
        useJUnitPlatform()
    }

    tasks.named("compileJava").configure {
        dependsOn(tasks.named("spotlessJavaApply"))
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            // google-java-format は 2 スペースインデント
            // AOSP は 4 スペースインデント
            googleJavaFormat("1.9").aosp()
        }
    }
}
