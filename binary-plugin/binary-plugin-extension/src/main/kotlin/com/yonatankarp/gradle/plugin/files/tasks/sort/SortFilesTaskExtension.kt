package com.yonatankarp.gradle.plugin.files.tasks.sort

import org.gradle.api.provider.Property

/**
 * Extension that will be used in plugin for sorting files task configuration.
 */
interface SortFilesTaskExtension {
    /**
     * @return Property that holds sorting type.
     */
    abstract val sortType: Property<String>

    /**
     * @return Property that holds location to the directory containing files to be sorted.
     */
    abstract val directoryLocation: Property<String>
}

