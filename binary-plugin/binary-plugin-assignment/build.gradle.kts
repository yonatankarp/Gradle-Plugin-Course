/**
 * java-gradle-plugin - Core Gradle Plugin, <a href="https://docs.gradle.org/current/userguide/java_gradle_plugin.html">Java Gradle Plugin</a>.
 * It simplifies compilation and publishing of Gradle binary plugins.
 *
 * maven-publish - Core Gradle Plugin, <a href="https://docs.gradle.org/current/userguide/publishing_maven.html">Java Gradle Plugin</a>Maven Publish</a>
 * plugin will make it possible to publish artifacts to Maven repositories.
 */
plugins {
  `java-gradle-plugin`
  id("maven-publish")
  kotlin("jvm") version "1.9.0"
}

repositories {
  mavenCentral()
}

/**
 * An extension for java-gradle-plugin in which we define all the plugins that this project will create.
 * Each plugin should have name, id and an implementationClass defined.
 * During the jar task it will be verified that plugin descriptor is correct. implementationClass has to be an existing
 * class implementing Plugin interface.
 */
gradlePlugin {
  plugins {
    create("filesPlugin") {
      id = "com.yonatankarp.files-plugin"
      implementationClass = "com.yonatankarp.gradle.plugin.files.FilesPlugin"
    }
  }
}

/**
 * In publishing block we can define specific package repositories where the artifacts will be published.
 * As in our case we have Maven Archiva running locally we specify local URL,
 * name of the repository so that we can find publishing task easily and credentials as Archiva requires
 * them by default.
 */
publishing {
  repositories {
    gradlePluginPortal()
    maven {
      url = uri("http://localhost:8080/repository/internal")
      name = "mavenArchivaPrivate"
      isAllowInsecureProtocol = true
//      credentials {
//        username = "$privateArchivaUser"
//        password = "$privateArchivaPassword"
//      }
    }
  }
}
