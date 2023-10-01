plugins {
    id("com.yonatankarp.files-plugin") version "0.2.0"
}

group = "com.yonatankarp"
version = "0.1.0"

sortFiles {
    sortType.set(project.extensions.extraProperties.get("tasks.files.sortType") as String)
    directoryLocation.set(project.extensions.extraProperties.get("tasks.files.folder") as String)
}
