package com.yonatankarp.gradle.plugin.files.tasks.sort.mapper

import java.io.File

/**
 * Maps file extension to directory name.
 */
class FileDirectoryExtensionMapper : FileDirectoryMapper {
    override fun getDirectory(file: File): String {
        return getExtensionOf(file.getName())
    }

    /**
     * Get file extension from its file name.
     *
     * If filename is image.jpg then the method will return "jpg".
     *
     * @param filename from which extension will be extracted
     * @return extension of the file
     */
    private fun getExtensionOf(filename: String): String {
        return filename.substring(filename.lastIndexOf(".") + 1)
    }
}

