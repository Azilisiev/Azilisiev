#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

global destinationPath = "C:\Users\Awen\Downloads"
global originPath = "C:\Program Files (x86)\Steam\userdata\155544677\1465360\remote"
global fileName = "\CompleteSave.cfg"

promptPaths(){
    global destinationPath
    global originPath
    InputBox, originPath, originPath
    InputBox, destinationPath, destinationPath
}

transferSave(){

    global destinationPath
    global originPath

    Msgbox 4, Confirm, Are you sure you want to transfer data from file in %originPath% to %destinationPath% ?
    
    originFile := FileOpen(originPath.fileName, "rw")
    destinationFile := FileOpen(destinationPath.fileName, "rw")


    saveInfos := originFile.Read()
    replaceInfos := destinationFile.Read()

    RegExMatch(saveInfos,"finishedObjs.*],", saveInfos)
    replaceInfos := RegExReplace(replaceInfos, "finishedObjs.*]," , saveInfos)

    RegExMatch(saveInfos,"discoveredObjectives.*],", saveInfos)
    replaceInfos := RegExReplace(replaceInfos, "discoveredObjectives.*]," , saveInfos)

    RegExMatch(saveInfos,"viewedUnactivatedObjectives.*],", saveInfos)
    replaceInfos := RegExReplace(replaceInfos, "viewedUnactivatedObjectives.*]," , saveInfos)

    RegExMatch(saveInfos,"discoveredUpgrades.*],", saveInfos)
    replaceInfos := RegExReplace(replaceInfos, "discoveredUpgrades.*]," , saveInfos)
    
    RegExMatch(saveInfos,"watchPointsData.*}}},", saveInfos)
    replaceInfos := RegExReplace(replaceInfos, "watchPointsData.*}}}," , saveInfos)
    

    

    originFile.Close()
    destinationFile.Close()

    FileDelete, %destinationPath%
    FileAppend ,%replaceInfos%,%destinationPath%




}
^Numpad1::promptPaths()
^Numpad0::transferSave()
^r::Reload