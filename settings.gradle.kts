rootProject.name = "gradle-plugin-course"

include(
    "task:file-tasks-assignment",
    "task:file-tasks-assignment-done",
    "script-plugin:local-script-plugin-example",
    "script-plugin:remote-script-plugin-example",
    "precompiled-script:file-tasks-assignment",
    "precompiled-script:file-tasks-assignment-done",
    "binary:file-tasks-assignment",
    "binary:file-tasks-assignment-done",
)
