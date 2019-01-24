plugins {
  id("org.metaborg.gradle.config.root-project") version "0.5.0"
  id("org.metaborg.gitonium") version "0.3.0"
  kotlin("jvm") version "1.3.20" apply false // Apply only in sub-projects.
}

subprojects {
  metaborgConfig {
    configureSubProject()
    configureKotlinLibrary()
  }
}
