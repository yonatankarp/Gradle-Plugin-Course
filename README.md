# Gradle Plugin Development

This repository contains solutions for the assignments
from [Gradle Plugin Development](https://www.udemy.com/course/gradle-development/) Udemy course.

All solutions are written using **Gradle Kotlin DSL** build scripts and additional code in **Java 17**. Projects were
created with IntelliJ IDEA.
Every project comes bundled with [Gradle wrapper version 8.2](https://docs.gradle.org/8.2/release-notes.html) or higher.

This repository also includes the original solutions of the course
author [Andrey Hihlovskiy](https://www.udemy.com/user/andrey-hihlovskiy/) written in Groovy. All solutions appears under
a `<TASK_NAME>-done` submodule (e.g. [file-tasks-assignment-done](task/file-tasks-assignment-done))

# Sections

Below is the menu for easier navigation between sections. Every section contains multiple examples or assignments with
the focus on a particular topic.

| Module                                                     |                                                                                                      Description                                                                                                      |                                                                                          External Link |
|------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|-------------------------------------------------------------------------------------------------------:|
| **[task](task)**                                           |                           This directory contains assignments and examples with the focus on Gradle tasks. The task is sorting files. Those tasks will be the base for plugin development.                            | [Task Assignment](https://www.udemy.com/course/gradle-development/learn/practice/1322992/introduction) |
| **[script-plugin](script-plugin)**                         |                                             Script plugin is the easiest possible way of extracting tasks and any additional build logic from original build.gradle file.                                             |       [Script Plugin Lectures](https://www.udemy.com/course/gradle-development/learn/lecture/25307352) |
| **[precompiled-script-plugin](precompiled-script-plugin)** |                                                                   Going step further one can extract script plugin into precompiled script plugin.                                                                    |   [Precompiled plugin lecture](https://www.udemy.com/course/gradle-development/learn/lecture/27025626) |
| **[binary-plugin](binary-plugin)**                         | Binary plugin is the most powerful way of writing and packaging a plugin. You'll be able to share the plugin within your own private maven repository or through [Gradle Plugin Portal](https://plugins.gradle.org/). |       [Binary Plugin Lectures](https://www.udemy.com/course/gradle-development/learn/lecture/28264772) |

For any changes or additional content check [release notes](https://github.com/rivancic/gradle/releases).

If you spot any issue in descriptions or source code, if you have any suggestions don't hesitate and
open [an issue](https://github.com/rivancic/gradle/issues/new).
