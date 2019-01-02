import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.10" apply false
  id("org.metaborg.gitonium") version "0.3.0"
}

subprojects {
  group = "org.metaborg"

  repositories {
    maven(url = "http://home.gohla.nl:8091/artifactory/all/")
  }

  apply(plugin = "kotlin")
  tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
  }

  val compile by configurations
  dependencies {
    compile(kotlin("stdlib"))
  }

  apply(plugin = "maven-publish")
  configure<PublishingExtension> {
    publications {
      create<MavenPublication>("Default") {
        from(components["java"])
      }
    }
    repositories {
      maven {
        name = "Artifactory"
        url = uri("http://home.gohla.nl:8091/artifactory/all/")
        credentials {
          username = project.findProperty("publish.repository.Artifactory.username")?.toString()
          password = project.findProperty("publish.repository.Artifactory.password")?.toString()
        }
      }
    }
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
