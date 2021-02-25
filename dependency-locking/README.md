# Dependency locking

since: 4.8

<https://docs.gradle.org/current/userguide/dependency_locking.html>

## 有効化

```kotlin
dependencyLocking {
    lockAllConfigurations()
}
```

## 依存関係をロックする

```powershell
./gradlew dependencies --write-locks
```

## Lock state files

`gradle/dependency-locks/<configuration name>.lockfile`
