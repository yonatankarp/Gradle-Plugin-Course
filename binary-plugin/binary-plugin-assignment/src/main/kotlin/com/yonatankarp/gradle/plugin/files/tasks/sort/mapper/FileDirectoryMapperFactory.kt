package com.yonatankarp.gradle.plugin.files.tasks.sort.mapper

import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project


object FileDirectoryMapperFactory {
    /**
     * Fetches sorting type from [tasks.files.sortType] properties. If property is not present then files will be sorted by the date.
     *
     * @param project holding properties.
     * @return FileDirectoryMapper that actually defines sorting algorithm
     */
    fun getFileDirectoryMapper(project: Project): FileDirectoryMapper {
        val sortType: String
        return if (project.hasProperty("tasks.files.sortType")) {
            sortType =
                project.extensions.extraProperties["tasks.files.sortType"].toString()
            when(sortType) {
                "extension" -> FileDirectoryExtensionMapper()
                "date" -> FileDirectoryDateMapper()
                "alphabet" -> FileDirectoryAlphabetMapper()
                else -> throw InvalidUserDataException("Invalid property tasks.files.sortType value provided [$sortType]. Valid values are ['extension','date','alphabet']")
            }
        } else {
            project.logger.quiet("Property [tasks.files.sortType] isn't set, default sorting will be done by creation date")
            FileDirectoryDateMapper()
        }
    }
}

