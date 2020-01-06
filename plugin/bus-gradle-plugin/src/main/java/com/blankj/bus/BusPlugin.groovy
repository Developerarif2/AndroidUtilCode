package com.blankj.bus

import com.android.build.gradle.AppExtension
import com.blankj.bus.util.LogUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class BusPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (project.plugins.hasPlugin("com.android.application") || project.plugins.hasPlugin("com.android.dynamic-feature")) {
            LogUtils.init(project)
            LogUtils.l('project(' + project.toString() + ') apply bus gradle plugin!')
            project.extensions.create(Config.EXT_NAME, BusExtension)
            def android = project.extensions.getByType(AppExtension)
            android.registerTransform(new BusTransform(project))
        }
    }
}