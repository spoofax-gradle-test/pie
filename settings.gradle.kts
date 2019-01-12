rootProject.name = "pie"

pluginManagement {
  repositories {
    maven(url = "http://home.gohla.nl:8091/artifactory/all/")
    gradlePluginPortal()
  }
}

include("pie.api")
include("pie.runtime")

project(":pie.api").projectDir = file("api")
project(":pie.runtime").projectDir = file("runtime")
