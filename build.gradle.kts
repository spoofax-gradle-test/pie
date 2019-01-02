import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.10" apply false
  id("org.metaborg.gitonium") version "0.3.0"
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
