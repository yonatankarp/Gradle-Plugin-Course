package com.yonatankarp.gradle.plugin.files.tasks.clean

import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.TaskAction

open class CleanTask : Delete() {
    companion object {
        private const val FILES_GROUP_NAME = "files"
        private const val CLEAN_TASK_DESCRIPTION = "Clean build directory"
    }

    init {
        group = FILES_GROUP_NAME
        description = CLEAN_TASK_DESCRIPTION
        delete(project.layout.buildDirectory)
    }
}
