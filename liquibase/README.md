# Liquibase

- Home page: <https://www.liquibase.org/>
- Documentation: <https://docs.liquibase.com/home.html>
- Liquibase Gradle Plugin:
    - Gradle Plugin Portal :<https://plugins.gradle.org/plugin/org.liquibase.gradle>
    - Source Repository: <https://github.com/liquibase/liquibase-gradle-plugin>
- Dependencies:
    - <https://search.maven.org/artifact/org.liquibase/liquibase-core>
    - <https://search.maven.org/artifact/org.liquibase/liquibase-groovy-dsl>

## 検証環境

検証に使用した環境を以下に示す。

```plaintext
> ./gradlew -v

------------------------------------------------------------
Gradle 6.8.2
------------------------------------------------------------

Build time:   2021-02-05 12:53:00 UTC
Revision:     b9bd4a5c6026ac52f690eaf2829ee26563cad426

Kotlin:       1.4.20
Groovy:       2.5.12
Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
JVM:          15 (AdoptOpenJDK 15+36)
OS:           Windows 10 10.0 amd64
```

## テーブル

Liquibase によって作成されるテーブルは以下の 2 テーブル:

- [DATABASECHANGELOG](https://docs.liquibase.com/concepts/databasechangelog-table.html)

    実行されたチェンジセットを監視します。

- [DATABASECHANGELOGLOCK](https://docs.liquibase.com/concepts/databasechangeloglock-table.html)

    Liquibase のインスタンスが一度に 1 つだけ実行されるようにします。

## ユースケース

- 最初から Liquibase を導入する
- 既存のデータベースに Liquibase を導入する

## 最初から Liquibase を導入する

DB を起動する:

```shell
pushd postgresql
docker-compose up -d
poppd
```

`gradle.properties` ファイルを作成して接続情報を定義する:

```properties
dbUrl = jdbc:postgresql://localhost/<DB名>
dbUsername = <ユーザー名>
dbPassword = <パスワード>
```

`update` タスクを実行する:

```shell
./gradlew update

> Task :update
liquibase-plugin: Running the 'test' activity...
Liquibase Community 4.3.1 by Datical
####################################################
##   _     _             _ _                      ##
##  | |   (_)           (_) |                     ##
##  | |    _  __ _ _   _ _| |__   __ _ ___  ___   ##
##  | |   | |/ _` | | | | | '_ \ / _` / __|/ _ \  ##
##  | |___| | (_| | |_| | | |_) | (_| \__ \  __/  ##
##  \_____/_|\__, |\__,_|_|_.__/ \__,_|___/\___|  ##
##              | |                               ##
##              |_|                               ##
##                                                ## 
##  Get documentation at docs.liquibase.com       ##
##  Get certified courses at learn.liquibase.com  ## 
##  Free schema change activity reports at        ##
##      https://hub.liquibase.com                 ##
##                                                ##
####################################################
Starting Liquibase at 00:08:47 (version 4.3.1 #26 built at 2021-02-12 17:41+0000)
Liquibase: Update has been successful.
```

## 既存のデータベースに Liquibase を導入する

- チェンジログを適用済みとしてマークする（ `changelogSync` コマンド）

- 既存のデータベースからチェンジログを生成する（ `generateChangeLog` コマンド）

## コマンド

参考:

- [Liquibase Community Commands Homepage | Liquibase Docs](https://docs.liquibase.com/commands/community/home.html) を参照してください。

## Gradle タスク一覧

```plaintext
Liquibase tasks
---------------
calculateCheckSum - Calculates and prints a checksum for the <liquibaseCommandValue> changeset with the given id in the format filepath::id::author.
changelogSync - Mark all changes as executed in the database.
changelogSyncSQL - Writes SQL to mark all changes as executed in the database to STDOUT.
clearChecksums - Removes all saved checksums from the database. On next run checksums will be recomputed.  Useful for "MD5Sum Check Failed" errors.
dbDoc - Generates Javadoc-like documentation based on current database and change log to the <liquibaseCommandValue> directory.
diff - Writes description of differences to standard out.
diffChangeLog - Writes Change Log to update the database to the reference database to standard out
dropAll - Drops all database objects owned by the user. Note that functions, procedures and packages are not dropped (Liquibase limitation)
executeSql - Executes SQL in the database given in <liquibaseCommandValue> in this format: -PliquibaseCommandValue="--sql=select 1" or PliquibaseCommandValue="--sqlFile=myfile.sql"
futureRollbackCountSQL - Writes SQL to roll back <liquibaseCommandValue> changes the database after the changes in the changelog have been applied.
futureRollbackSQL - Writes SQL to roll back the database to the current state after the changes in the changeslog have been applied.
generateChangelog - Writes Change Log groovy to copy the current state of the database to standard out.
listLocks - Lists who currently has locks on the database changelog.
markNextChangesetRan - Mark the next change set as executed in the database.
markNextChangesetRanSQL - Writes SQL to mark the next change set as executed in the database to STDOUT.
releaseLocks - Releases all locks on the database changelog.
rollback - Rolls back the database to the state it was in when the <liquibaseCommandValue> tag was applied.
rollbackCount - Rolls back the last <liquibaseCommandValue> change sets.
rollbackCountSQL - Writes SQL to roll back the last <liquibaseCommandValue> change sets to STDOUT.
rollbackSQL - Writes SQL to roll back the database to the state it was in when the <liquibaseCommandValue> tag was applied to STDOUT.
rollbackToDate - Rolls back the database to the state it was in at the <liquibaseCommandValue> date/time.
rollbackToDateSQL - Writes SQL to roll back the database to the state it was in at the <liquibaseCommandValue> date/time to STDOUT.
snapshot - Writes the current state of the database to standard out
snapshotReference - Writes the current state of the referenceUrl database to standard out
status - Outputs count (list if liquibaseCommandValue is "--verbose") of unrun change sets.
tag - Tags the current database state with <liquibaseCommandValue> for future rollback.
tagExists - Checks whether the tag given in <liquibaseCommandValue> is already existing.
unexpectedChangeSets - Outputs count (list if liquibaseCommandValue is "--verbose") of changesets run in the database that do not exist in the changelog.
update - Updates the database to the current version.
updateCount - Applies the next <liquibaseCommandValue> change sets.
updateCountSql - Writes SQL to apply the next <liquibaseCommandValue> change sets to STDOUT.
updateSQL - Writes SQL to update the database to the current version to STDOUT.
updateTestingRollback - Updates the database, then rolls back changes before updating again.
updateToTag - Updates the database to the changeSet with the <liquibaseCommandValue> tag
updateToTagSQL - Writes (to standard out) the SQL to update to the changeSet with the <liquibaseCommandValue> tag
validate - Checks the changelog for errors.
```
