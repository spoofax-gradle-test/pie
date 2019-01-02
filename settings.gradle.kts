rootProject.name = "pie"

pluginManagement {
  repositories {
    gradlePluginPortal()
    maven(url = "http://home.gohla.nl:8091/artifactory/all/")
  }
}

include("pie.api")
include("pie.runtime")

project(":pie.api").projectDir = file("api")
project(":pie.runtime").projectDir = file("runtime")
