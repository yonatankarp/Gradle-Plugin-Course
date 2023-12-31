/**
 * Remember local script plugins can be applied with the apply syntax containing map with the key from,
 * and the relative file path as the key.
 *
 * From the applied taskPlugin this Gradle project will have registered 'sortFiles' and 'clean' task.
 */
apply {
    from("gradle/filesPlugin.gradle.kts")
}

logger.quiet("=============================================================================================")
logger.quiet("                               Main Build Script                                             ")
logger.quiet("=============================================================================================")
