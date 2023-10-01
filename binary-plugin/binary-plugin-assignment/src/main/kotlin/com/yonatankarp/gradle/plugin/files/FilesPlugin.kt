package com.yonatankarp.gradle.plugin.files

import com.yonatankarp.gradle.plugin.files.tasks.clean.CleanTask
import com.yonatankarp.gradle.plugin.files.tasks.sort.SortFilesTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class FilesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.logger.info("===========================================================================")
        project.logger.info("                            Binary Files Plugin                            ")
        project.logger.info("===========================================================================")

        // Check if clean task is already registered. Then don't add it. As it
        // can conflict with java clean task if java plugin is applied to the
        // same project.
        if (project.tasks.findByName(CLEAN_TASK_NAME) == null) {
            project.tasks.register(CLEAN_TASK_NAME, CleanTask::class.java)
        }

        project.tasks
            .register(SORT_FILES_TASK_NAME, SortFilesTask::class.java)
            .configure { action ->
                action.dependsOn(
                    project.tasks
                        .getByName(CLEAN_TASK_NAME)
                )
            }
    }

    companion object {
        private const val CLEAN_TASK_NAME = "clean"
        private const val SORT_FILES_TASK_NAME = "sortFiles"
    }
}
