package com.blankj.bus

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.blankj.bus.util.JsonUtils
import com.blankj.bus.util.LogUtils
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

class BusTransform extends Transform {

    Project mProject;

    BusTransform(Project project) {
        mProject = project
    }

    @Override
    String getName() {
        return "busTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation)
            throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        LogUtils.l(getName() + " started")
        long stTime = System.currentTimeMillis()

        def ext = mProject[Config.EXT_NAME] as BusExtension
        LogUtils.l("busExtension: $ext")
        if (ext.busUtilsClass.trim().equals("")) {
            throw new Exception("BusExtension's busUtilsClass is empty.")
        }
        File jsonFile = new File(mProject.projectDir.getAbsolutePath(), "__bus__.json")
        FileUtils.write(jsonFile, "{}")

        def inputs = transformInvocation.getInputs()
        def referencedInputs = transformInvocation.getReferencedInputs()
        def outputProvider = transformInvocation.getOutputProvider()
        def isIncremental = transformInvocation.isIncremental()

        outputProvider.deleteAll()

        BusScan busScan = new BusScan(ext.busUtilsClass)

        inputs.each { TransformInput input ->
            input.directoryInputs.each { DirectoryInput dirInput ->// 遍历文件夹
                File dir = dirInput.file

                def dest = outputProvider.getContentLocation(
                        dirInput.name,
                        dirInput.contentTypes,
                        dirInput.scopes,
                        Format.DIRECTORY
                )
                FileUtils.copyDirectory(dir, dest)

                LogUtils.l("scan dir: ${dirInput.file} -> $dest")

                busScan.scanDir(dest)
            }
            input.jarInputs.each { JarInput jarInput ->// 遍历 jar 文件
                File jar = jarInput.file

                def jarName = jarInput.name
                def dest = outputProvider.getContentLocation(
                        jarName,
                        jarInput.contentTypes,
                        jarInput.scopes,
                        Format.JAR
                )
                FileUtils.copyFile(jar, dest)

                if (jumpScan(jarName)) {
                    LogUtils.l("jump jar: $jarName -> $dest")
                    return
                }

                LogUtils.l("scan jar: $jarName -> $dest")
                busScan.scanJar(dest)
            }
        }

        if (busScan.busUtilsTransformFile != null) {
            if (busScan.busMap.isEmpty()) {
                LogUtils.l("no bus.")
            } else {
                Map<String, String> rightBus = [:]
                Map wrongBus = [:]
                busScan.busMap.each { String tag, List<BusInfo> infoList ->
                    if (infoList.size() == 1) {
                        BusInfo busInfo = infoList.get(0)
                        if (busInfo.isParamSizeNoMoreThanOne) {
                            rightBus.put(tag, busInfo.toString())
                        } else {
                            wrongBus.put(tag, busInfo.toString())
                        }
                    } else {
                        List<String> infoString = []
                        infoList.each { BusInfo info ->
                            infoString.add(info.toString())
                        }
                        wrongBus.put(tag, infoString)
                    }
                }
                Map busDetails = [:]
                busDetails.put("BusUtilsClass", ext.busUtilsClass)
                busDetails.put("rightBus", rightBus)
                busDetails.put("wrongBus", wrongBus)
                String busJson = JsonUtils.getFormatJson(busDetails)
                LogUtils.l(jsonFile.toString() + ": " + busJson)
                FileUtils.write(jsonFile, busJson)

                if (wrongBus.size() > 0) {
                    if (ext.abortOnError) {
                        throw new Exception("These buses is not right: " + wrongBus +
                                "\n u can check it in file: " + jsonFile.toString())
                    }
                }

                BusInject.start(busScan.busMap, busScan.busUtilsTransformFile, ext.busUtilsClass)
            }
        } else {
            throw new Exception("No BusUtils of ${ext.busUtilsClass} in $mProject.")
        }

        LogUtils.l(getName() + " finished: " + (System.currentTimeMillis() - stTime) + "ms")
    }

    private static jumpScan(String jarName) {
        boolean isExcept = false
        for (String except : Config.EXCEPTS) {
            if (jarName.startsWith(except)) {
                isExcept = true
                break
            }
        }
        return isExcept
    }
}