package com.yonatankarp.gradle.plugin.files.tasks.sort.mapper

import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.provider.Provider

/**
 * Class containing static factory method [.getFileDirectoryMapper] that will return concrete
 * [FileDirectoryMapper] instance based on the sorting type.
 */
object FileDirectoryMapperFactory {
    /**
     * Factory method that based on provided sorting type parameter returns concrete FileDirectoryMapper.
     * If property isn't present then files will be sorted by the date ([FileDirectoryDateMapper]).
     *
     * @param logger for logging warning about misconfigured plugin.
     * @param sortType based on which FileDirectoryMapper will be chosen.
     * @return FileDirectoryMapper that actually defines sorting algorithm
     */
    fun getFileDirectoryMapper(
        logger: Logger,
        sortType: Provider<String>
    ): FileDirectoryMapper {
        return if (sortType.isPresent) {
            when(sortType.get()) {
                "extension" -> FileDirectoryExtensionMapper()
                "date" -> FileDirectoryDateMapper()
                "alphabet" -> FileDirectoryAlphabetMapper()
                else -> throw InvalidUserDataException("Invalid property sortType value provided [$sortType]. Valid values are ['extension','date','alphabet']")
            }
        } else {
            logger.quiet("Property sortType isn't set, default sorting will be done by creation date")
            FileDirectoryDateMapper()
        }
    }
}
