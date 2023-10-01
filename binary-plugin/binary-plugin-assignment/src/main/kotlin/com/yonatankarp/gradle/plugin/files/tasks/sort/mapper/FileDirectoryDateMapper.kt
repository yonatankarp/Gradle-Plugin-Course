package com.yonatankarp.gradle.plugin.files.tasks.sort.mapper

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.FileTime
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Maps file creation date to directory name.
 */
class FileDirectoryDateMapper : FileDirectoryMapper {
    override fun getDirectory(file: File): String {
        val creationTime =
            Files.getAttribute(Paths.get(file.path), "creationTime") as FileTime
        return fileCreationDateFormat.format(creationTime.toLocalDateUTC())
    }

    private fun FileTime.toLocalDateUTC() =
        Instant
            .ofEpochMilli(this.toMillis())
            .atZone(ZoneOffset.UTC)
            .toLocalDate()

    companion object {
        private val fileCreationDateFormat = DateTimeFormatter.ofPattern("MM-YYYY")
    }
}
