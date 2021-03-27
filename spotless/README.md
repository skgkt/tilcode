# Spotless

コード整形ツール

- Source repository: <https://github.com/diffplug/spotless>
- Gradle Plugin Portal: <https://plugins.gradle.org/plugin/com.diffplug.spotless>
- 参考:
    - google-java-format: <https://github.com/google/google-java-format/releases>

`v5.0.0` にてプラグイン ID が変わっています。

- `v5.0.0` より前: `com.diffplug.gradle.spotless`
- `v5.0.0` 以降: `com.diffplug.spotless`

## 定義

```kotlin
plugins {
    id("com.diffplug.spotless") version "5.11.0"
}

spotless {
    java {
        // AOSP style
        googleJavaFormat("1.9").aosp()
    }
}
```

## コード整形実行タスク

```powershell
./gradlew spotlessApply
```

## コンパイル時にフォーマットする

```kotlin
tasks.named("compileJava").configure {
    dependsOn(tasks.named("spotlessJavaApply"))
}
```
