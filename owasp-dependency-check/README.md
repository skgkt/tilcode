# OWASP Dependency-Check

- オフィシャルサイト: [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)
- Gradle プラグインポータル: [Gradle - Plugin: org.owasp.dependencycheck](https://plugins.gradle.org/plugin/org.owasp.dependencycheck)
- 参考:
    - <https://www.cvedetails.com/>

## Tasks

```plaintext
$ ./gradlew tasks

OWASP dependency-check tasks
----------------------------
dependencyCheckAggregate - Identifies and reports known vulnerabilities (CVEs) in multi-project dependencies.
dependencyCheckAnalyze - Identifies and reports known vulnerabilities (CVEs) in project dependencies.
dependencyCheckPurge - Purges the local cache of the NVD.
dependencyCheckUpdate - Downloads and stores updates from the NVD CVE data feeds.
```

## ローカルキャッシュ

ローカルキャッシュは H2 のデータベースで保存されているみたい。

パス: `~/.gradle/dependency-check-data/5.0/odc.mv.db`

ソース: <https://github.com/jeremylong/DependencyCheck/blob/main/core/src/main/resources/dependencycheck.properties#L19>

## サンプル

脆弱性が多い Struts で試してみる。  
CVE Details をもとに脆弱性が報告されているバージョンを調べると `2.5.16` で脆弱性（以下）が報告されていた。

[CVE-2018-11776 : Apache Struts versions 2.3 to 2.3.34 and 2.5 to 2.5.16 suffer from possible Remote Code Execution when alwaysSelectFullN](https://www.cvedetails.com/cve/CVE-2018-11776/)

`dependencyCheckAnalyze` タスクを実行すると、以下のように出力された。  
また、 HTML 形式のレポートも `build/reports/dependency-check-report.html` に出力された。

```plaintext
> Task :dependencyCheckAnalyze
Verifying dependencies for project owasp-dependency-check
Checking for updates and analyzing dependencies for vulnerabilities
Generating report for project owasp-dependency-check
Found 5 vulnerabilities in project owasp-dependency-check


One or more dependencies were identified with known vulnerabilities in owasp-dependency-check:

log4j-api-2.10.0.jar (pkg:maven/org.apache.logging.log4j/log4j-api@2.10.0, cpe:2.3:a:apache:log4j:2.10.0:*:*:*:*:*:*:*) : CVE-2020-9488
struts2-core-2.5.16.jar (pkg:maven/org.apache.struts/struts2-core@2.5.16, cpe:2.3:a:apache:struts:2.5.16:*:*:*:*:*:*:*) : CVE-2018-11776, CVE-2019-0230, CVE-2019-0233, CVE-2020-17530


See the dependency-check report for more details.
```
