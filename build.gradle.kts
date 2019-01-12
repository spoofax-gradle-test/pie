plugins {
  id("org.metaborg.gradle.config") version "0.4.1"
  id("org.metaborg.gitonium") version "0.3.0"
  kotlin("jvm") version "1.3.11" apply false // Apply only in sub-projects. Use version 1.3.11 for compatibility with Gradle 5.1.
}

subprojects {
  metaborgConfig {
    configureSubProject()
    configureKotlinLibrary()
  }
}
