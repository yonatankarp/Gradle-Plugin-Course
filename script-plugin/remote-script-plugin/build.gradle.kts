/**
 * Apply remote versioned Gradle script plugin that contains tasks for sorting files.
 *
 * The syntax is the same as importing script plugin. The only difference is that based on the available option we
 * can provide specific version of that plugin to be downloaded.
 *
 * Version is configurable in that it's specified as [task.plugin.version] property in gradle.properties file and
 * interpolated in a Groovy String.
 */
apply {
    // Link can be found by going to the file and press the `Raw` button.
    from("https://raw.githubusercontent.com/yonatankarp/Gradle-Plugin-Course-Remote-Script/${project.properties["tasks.plugin.version"].toString()}/filesPlugin.gradle.kts")
}
