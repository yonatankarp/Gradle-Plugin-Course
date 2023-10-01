plugins {
    id("com.yonatankarp.files-plugin") version "0.1.0"
}

tasks.named("sortFiles") {
//    val sortType = project.extensions.extraProperties["tasks.files.sortType"] as String
//    val directoryLocation = project.extensions.extraProperties["tasks.files.folder"] as String
}

group = "com.yonatankarp"
version = "0.1.0"
