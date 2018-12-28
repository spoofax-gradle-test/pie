import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// HACK: load our plugin via buildscript classpath and apply to work around IntelliJ bug which prevents custom plugins in composite builds.
buildscript {
  repositories {
    flatDir { dirs("../gitonium/build/libs") }
    mavenCentral()
    jcenter()
  }
  dependencies {
    classpath("org.metaborg", "gitonium", "develop-SNAPSHOT")
    classpath("org.eclipse.jgit:org.eclipse.jgit:5.2.0.201812061821-r")
  }
}
apply {
  plugin("org.metaborg.gitonium")
}

plugins {
  kotlin("jvm") version "1.3.10" apply false
}
subprojects {
  group = "org.metaborg"
  repositories {
    mavenCentral()
    jcenter()
  }
  apply {
    plugin("kotlin")
  }
  tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
  }
  val compile by configurations
  dependencies {
    compile(kotlin("stdlib"))
  }
}
tasks {
  register("buildAll") {
    dependsOn(subprojects.map { it.tasks["build"] })
  }
  register("cleanAll") {
    dependsOn(subprojects.map { it.tasks["clean"] })
  }
}
