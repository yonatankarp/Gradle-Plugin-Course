package com.yonatankarp.gradle.plugin.files.tasks.sort.mapper

import java.io.File
import java.io.IOException

/**
 * Implements mapping a file to specific directory name. The logic is based on the sorting algorithm.
 */
interface FileDirectoryMapper {
    fun getDirectory(file: File): String
}
