/**
 * In order that the precompiled plugin is compiled and made available to other projects <b>java-gradle-plugin</b> has
 * to be applied in <b>buildSrc</b> itself.
 *
 * This is the place where any additional plugins and configuration can be applied that will be required to
 * compile a Gradle plugin.
 */
plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
}
