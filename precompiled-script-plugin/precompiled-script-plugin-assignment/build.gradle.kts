/**
 * Name of the precompiled script plugin for which the source code resides in
 * the <b>buildSrc</b> is defined by the file name of the script itself. In case
 * that the filename is <b>com.yonatankarp.sort-files-plugin.gradle.kts</b> Then
 * the plugin id will be filename without the extension (.gradle.kts).
 *
 * To run this project, you have to open the project as standalone project in
 * IntelliJ IDEA. otherwise the project will not recognize the <b>buildSrc</b>
 * folder, and the load of the project would fail.
 *
 * <ul>
 *   <li>Filename: com.yonatankarp.sort-files-plugin.gradle.kts</li>
 *   <li>Plugin id: com.yonatankarp.sort-files-plugin</li>
 *   </ul>
 */
plugins {
    id("com.yonatankarp.sort-files-plugin")
}
