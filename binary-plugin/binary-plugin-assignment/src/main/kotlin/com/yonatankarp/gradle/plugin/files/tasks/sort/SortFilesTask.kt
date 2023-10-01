package com.yonatankarp.gradle.plugin.files.tasks.sort

import com.yonatankarp.gradle.plugin.files.tasks.sort.mapper.FileDirectoryMapperFactory
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files
import java.nio.file.StandardCopyOption

open class SortFilesTask : DefaultTask() {

    companion object {
        private const val FILES_GROUP_NAME = "files"
        private const val SORT_TASK_DESCRIPTION = "Sorts files in given directory into build/files subdirectories based on the sorting type ['extension','date','alphabet']"
    }

    init {
        group = FILES_GROUP_NAME
        description = SORT_TASK_DESCRIPTION
    }

    @TaskAction
    fun apply() {
        project.logger.quiet("==== Sorting Files 🎉 ====")

        val fileMapper = FileDirectoryMapperFactory.getFileDirectoryMapper(project)

        project.extensions.extraProperties.get("tasks.files.folder")
            ?.let { project.file(it).listFiles() }
            ?.filter { it.isFile && !it.isHidden }
            ?.forEach { file ->
                project.logger.quiet("Filename: ${file.name}")
                runCatching {
                    val directory = fileMapper.getDirectory(file)
                    project.mkdir(project.layout.buildDirectory.dir("files/$directory/"))
                    Files.copy(
                        file.toPath(),
                        project.layout.buildDirectory.dir("files/$directory/${file.name}").get().asFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }.onFailure { project.logger.warn("Couldn't sort ${file.name} file properly.") }
            }
            ?: project.logger.error("Property [tasks.files.folder] is missing, and therefore no sorting was done.")

        project.logger.quiet("==========================")
    }
}
