/**
 * <a href="https://plugins.gradle.org/plugin/com.gradle.plugin-publish">com.gradle.plugin-publish</a> is a plugin that contains functionality for publishing Gradle plugins to Gradle Plugin Portal.
 * Since version 1.0.0-rc-1, it automatically includes java-gradle-plugin and maven-publish plugin
 */
plugins {
    `java-gradle-plugin`
    id("maven-publish")
    id("com.gradle.plugin-publish") version "1.2.1"
    kotlin("jvm") version "1.9.0"
}

group = "com.yonatankarp"
version = "0.2.0"

repositories {
    mavenCentral()
}

/**
 * An extension for java-gradle-plugin in which we define all the plugins that this project will create.
 * Each plugin should have name, id and an implementationClass defined.
 * During the jar task it will be verified that plugin descriptor is correct. implementationClass has to be an existing
 * class implementing Plugin interface.
 *
 * For publishing the plugin to the Gradle Plugin Portal you should provide meaningful <b>description</b> withing the specific plugin definition.
 * This information will be shown on the plugin detail page, and will help users to understand which functionality your plugin supports.
 */
gradlePlugin {

    // Metadata for publishing on the Gradle Plugin Portal. This <b>pluginBundle</b> extension is provided with the <b>com.gradle.plugin-publish</b> Gradle plugin.
    website = "https://github.com/rivancic/gradle"
    vcsUrl = "https://github.com/rivancic/gradle"

    plugins {
        create("filesPlugin") {
            id = "com.yonatankarp.files-plugin"
            displayName = "Files Sorting Plugin"
            description =
                "Plugin that can sort provided files based on a particular rule (alphabetically, creation date, extension)"
            implementationClass =
                "com.yonatankarp.gradle.plugin.files.FilesPlugin"
            tags.set(listOf("files", "sorting"))
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
        maven {
            url = uri("http://localhost:8080/repository/internal")
            name = "mavenArchivaPrivate"
            isAllowInsecureProtocol = true
        }
    }
}
