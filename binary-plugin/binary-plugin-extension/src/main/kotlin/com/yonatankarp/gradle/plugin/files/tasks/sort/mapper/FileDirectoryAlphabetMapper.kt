package com.yonatankarp.gradle.plugin.files.tasks.sort.mapper

import java.io.File

/**
 * Maps file initial to alphabetical ordered directory
 */
class FileDirectoryAlphabetMapper : FileDirectoryMapper {
    override fun getDirectory(file: File) = file.name.substring(0, 1)
}

