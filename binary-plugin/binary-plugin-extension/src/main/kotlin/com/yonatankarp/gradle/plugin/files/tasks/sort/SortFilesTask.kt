package com.yonatankarp.gradle.plugin.files.tasks.sort

import com.yonatankarp.gradle.plugin.files.tasks.sort.mapper.FileDirectoryMapperFactory
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * Implementation of Plugin Gradle API interface. In the {@link Plugin#apply(Object)} method we configure base plugin logic.
 * <p>
 * First its logged to the console so that we know plugin is being applied.
 * <p>
 * Next extension for parametrizing tasks is registered. Example of the extension:
 * <pre>
 * sortFilesExtension {
 *   sortType = ...
 *   directoryLocation = ...
 * }
 * </pre>
 *<p>
 * Then we register new tasks so that they are configured once they need to be invoked.
 *<p>
 * We have to check first before adding {@link CleanTask} task as it might already be added to the project if you would apply "java" plugin to the main project.
 *<p>
 * As the last part we register {@link SortFilesTask} task.
 */
abstract class SortFilesTask : DefaultTask() {


    /**
     * @return Property that represents file sorting type.
     */
    @Input
    abstract fun getSortType(): Property<String>

    /**
     * With correctly defined task inputs and outputs task can be skipped during the execution.
     * See [Up-to-date Checks - Gradle User guide](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks)
     *
     * @return DirectoryProperty pointing to directory containing files to be sorted.
     */
    @InputDirectory
    abstract fun getDirectoryLocation(): DirectoryProperty

    /**
     * @return DirectoryProperty specifying resulting directory where sorted files will be stored.
     */
    @OutputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    companion object {
        private const val FILES_GROUP_NAME = "files"
        private const val SORT_TASK_DESCRIPTION =
            "Sorts files in given directory into build/files subdirectories based on the sorting type ['extension','date','alphabet']"
    }

    init {
        group = FILES_GROUP_NAME
        description = SORT_TASK_DESCRIPTION
    }

    @TaskAction
    fun apply() {
        project.logger.quiet("==== Sorting Files 🎉 ====")

        /**
         * With correctly defined task inputs and outputs task can be skipped during the execution.
         * See [Up-to-date Checks - Gradle User guide](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks)
         *
         * @return DirectoryProperty pointing to directory containing files to be sorted.
         */
        project.delete(project.layout.buildDirectory.dir("files"))

        val fileMapper =
            FileDirectoryMapperFactory.getFileDirectoryMapper(project.logger, getSortType())

        getDirectoryLocation()
            .takeIf { it.isPresent }
            ?.let {
                getDirectoryLocation().get().asFile.listFiles()
                    ?.filter { it.isFile && !it.isHidden }
                    ?.forEach { file ->
                        project.logger.quiet("Filename: ${file.name}")
                        runCatching {
                            val directory = fileMapper.getDirectory(file)
                            project.mkdir(project.layout.buildDirectory.dir("files/$directory/"))
                            Files.copy(
                                file.toPath(),
                                getOutputDir().get().dir("files/$directory/${file.name}").asFile.toPath(),
                                StandardCopyOption.REPLACE_EXISTING
                            )
                        }.onFailure {
                            project.logger.warn(
                                "Couldn't sort ${file.name} file properly.",
                                it
                            )
                        }
                    }
            }
            ?: project.logger.error("Property [tasks.files.folder] is missing, and therefore no sorting was done.")

        project.logger.quiet("==========================")
    }
}

