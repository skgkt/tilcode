# Java Platform プラグイン

Gradle 5.2 で追加された Java Platform プラグインを試してみた。

- User Manual: <https://docs.gradle.org/current/userguide/java_platform_plugin.html>
- Release Notes: <https://docs.gradle.org/5.2/release-notes.html>
- 記事:
    - [Sharing dependency versions between projects](https://docs.gradle.org/current/userguide/platforms.html)

## 結論

Spring の Dependency Management プラグインの方が `dependencyManagement` タスクがあり、使いやすいと思う。

リンク:

- Reference Manual: <https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/>
- Gradle Plugin Portal: <https://plugins.gradle.org/plugin/io.spring.dependency-management>
- Source Repository: <https://github.com/spring-gradle-plugins/dependency-management-plugin>

## BOM をインポートする

`platform` を使用する。  
あくまでも推奨バージョンであるため、バージョンを指定した場合は Gradle の推移的依存関係が働く。  
Platform で定義したバージョンを強制したい場合は、 `enforcedPlatform` を使用する。（後述）

```kotlin
plugins {
    java
}

dependencies {
    // import a BOM
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.4.2"))

    implementation("org.springframework.boot:spring-boot-starter")
}
```

## 使い方

推奨バージョンの定義:

```kotlin
plugins {
    `java-platform`
}

dependencies {
    constraints {
        api("org.apache.poi:poi:5.0.0")
    }
}
```

他の BOM をインポートする:

Java Platform プラグインを適用したプロジェクトでは、 `dependencies` が許可されていないため、 BOM をインポートする場合は、以下のように定義する。  

```kotlin
plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    // import a BOM
    api(platform("org.springframework.boot:spring-boot-dependencies:2.4.2"))
}
```

バージョンを強制する:

`enforcedPlatform` を使用する。

```kotlin
plugins {
    java
}

dependencies {
    implementation(enforcedPlatform(project(":platform")))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.apache.poi:poi")
}
```
