package com.yonatankarp.gradle.plugin.files

import com.yonatankarp.gradle.plugin.files.tasks.clean.CleanTask
import com.yonatankarp.gradle.plugin.files.tasks.sort.SortFilesTask
import com.yonatankarp.gradle.plugin.files.tasks.sort.SortFilesTaskExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class FilesPlugin : Plugin<Project> {

    companion object {
        private const val CLEAN_TASK_NAME = "clean"
        private const val SORT_FILES_TASK_NAME = "sortFiles"
        private const val SORT_FILES_EXTENSION_NAME = "sortFiles"
        private const val SORT_FILES_SOURCE_FOLDER = "files"
        private const val SORT_FILES_TARGET_FOLDER = "files"
        private const val DEFAULT_SORTING_TYPE = "date"
    }

    override fun apply(project: Project) {
        project.logger.info("===========================================================================")
        project.logger.info("                        Binary (DSL) Files Plugin                          ")
        project.logger.info("===========================================================================")

        val sortFilesTaskExtension =
            project.extensions.create(
                SORT_FILES_EXTENSION_NAME,
                SortFilesTaskExtension::class.java
            )

        // Check if clean task is already registered. Then don't add it. As it
        // can conflict with java clean task if java plugin is applied to the
        // same project.
        if (project.tasks.findByName(CLEAN_TASK_NAME) == null) {
            project.tasks.register(CLEAN_TASK_NAME, CleanTask::class.java)
        }

        project.tasks.register(SORT_FILES_TASK_NAME, SortFilesTask::class.java)
            { task ->
                task.getDirectoryLocation().set(
                    project.file(
                        sortFilesTaskExtension.directoryLocation.orNull
                            ?: SORT_FILES_SOURCE_FOLDER
                    )
                )

                task.getSortType().set(
                    sortFilesTaskExtension.sortType.orNull
                        ?: DEFAULT_SORTING_TYPE
                )

                task.getOutputDir().set(
                    project.file(
                        project.layout.buildDirectory.dir(SORT_FILES_TARGET_FOLDER)
                    )
                )
            }
    }
}
