#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

filePath := "D:\divers Jeux\Tanks.csv"
fileVar := FileOpen(filePath, "r")
fileText := fileVar.Read()
tiers := [1,2,3,4,5,6,7,8,9,10]
nations := ["USA","UK","Germany","URSS","China","France","Japan","Tchec","Sweden","Italy","Poland"]
tankClasses := ["Light","Med","Heavy","TD","SPG"]
prem := false
no2List := ["T-34","E 75","matilda","T-54"]
mButtonEnabled := 0
mdown := 0

gui, MyGui:New, , RandomNamePicker
gui, Font, s15
gui, +LastFound
gui, Add, Button,y15 gFecthRandom vRdm, Get random tank !
gui, Add, Button, x+60 y15 gReloadList vRld, reload list
gui, Add, Button, x+60 y15 gActivateMButton vMBtn, activate M button


yValue = 80
For index,tier in tiers{

    Gui, Add, CheckBox, x50 y%yValue% gSelectTier v%tier% Checked , Tier %tier%
    yValue += 50
}
yValue = 80
For index,nation in nations{
    Gui, Add, CheckBox, x250 y%yValue% gSelectNation v%nation% Checked , %nation%
    yValue += 50
}
yValue = 80
For index,tankClass in tankClasses{
    Gui, Add, CheckBox, x450 y%yValue% gSelectClass v%tankClass% Checked , %tankClass%
    yValue += 50
}

Gui, Add, CheckBox, x650 y80 gSelectPrem v%prem% , prem

tiers := []
nations := []
tankClasses := []

gui, show, w750,
return

ReloadList(){
    global fileVar
    global fileText
    global filePath

    fileVar.Close()
    fileVar := FileOpen(filePath, "r")
    fileText := ""
    fileText := fileVar.Read()
}

FecthRandom(){
    global filePath
    global fileName
    global fileText
    global tiers
    global nations
    global tankClasses
    global prem

    tanks := fileText

    if(tiers.Length()>0){
        For index, tier in tiers{
            needle := ".*," . tier . ",.*`r`n"
            tanks := RegExReplace(tanks,needle, "")
        }
    }

    if(nations.Length()>0){
        For index, nation in nations{
            needle := ".*" . nation . ".*`r`n"
            tanks := RegExReplace(tanks,needle, "")

        }
    }

    if(tankClasses.Length()>0){
        For index, tankClass in tankClasses{
            needle := ".*" . tankClass . ".*`r`n"
            tanks := RegExReplace(tanks,needle, "")
        }
    }

    if(prem){
        needle := ".*False.*`r`n"
        tanks := RegExReplace(tanks,needle, "")
    }

    max := lineCount(tanks)
    Random, rndTankLine, 1, max-1
    tanksArray := StrSplit(tanks,"`r`n")
    choosenTank := tanksArray[rndTankLine]
    SelectTank(choosenTank)

}

SelectTier(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := ""){
    global tiers
    ControlGet isChecked, Checked,,,ahk_id %CtrlHwnd%
    if(isChecked){
        GuiControlGet tier, Name, % CtrlHwnd
        tiers.Remove(tier)
    }
    else{
        GuiControlGet tier, Name, % CtrlHwnd
        tiers.Push(tier)
    }

}

SelectNation(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := ""){
    global nations
    ControlGet isChecked, Checked,,,ahk_id %CtrlHwnd%
    if(isChecked){
        GuiControlGet nation, Name, % CtrlHwnd
        deleteString(nation,nations)
    }
    else{
        GuiControlGet nation, Name, % CtrlHwnd
        nations.Push(nation)
    }

}

SelectClass(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := ""){
    global tankClasses
    ControlGet isChecked, Checked,,,ahk_id %CtrlHwnd%
    if(isChecked){
        GuiControlGet tankClass, Name, % CtrlHwnd
        deleteString(tankClass,tankClasses)
    }
    else{
        GuiControlGet tankClass, Name, % CtrlHwnd
        tankClasses.Push(tankClass)
    }

}

SelectPrem(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := ""){
    global prem
    ControlGet isChecked, Checked,,,ahk_id %CtrlHwnd%
    if(isChecked){
        prem := true
    }
    else{
        prem := false
    }
}

SelectTank(str){
    global no2List
    tankInfos := StrSplit(str,",")
    WinActivate, Client de WoT
    MouseMove, 87, 907
    SendInput, {LButton Down}
    sleep 50
    SendInput, {LButton Up}
    Sleep, 200
    MouseMove,52, 951
    SendInput, {LButton Down}
    sleep 100
    SendInput, {LButton Up}
    Sleep, 300
    MouseMove,149, 891
    SendInput, {LButton Down}
    sleep 50
    SendInput, {LButton Up}
    sleep 200
    tankName := tankInfos[1]
    SendInput, %tankName%
    Sleep, 500
    if(!hasValue(no2List,tankName)){
        MouseMove,207, 960
        SendInput, {LButton Down}
        sleep 50
        SendInput, {LButton Up}
    }
    Else{
        MouseMove,218, 1003
        SendInput, {LButton Down}
        sleep 50
        SendInput, {LButton Up}
    }

}

RecoverCrew(){
    WinActivate, Client de WoT
    MouseMove, 29, 108
    SendInput, {LButton Down}
    sleep 100
    SendInput, {LButton Up}
    Sleep, 500
    MouseMove, 195, 352
    SendInput, {LButton Down}
    sleep 50
    SendInput, {LButton Up}

}

lineCount(str) {
    StrReplace(str, "`n", "`n", count)
    return count + 1
}

msgArray(array){
    arraytext := ""
    if(array.Length() > 0){
        for index,item in array{
            arraytext := arraytext "`r`n" item
        }
    }
    else {
        arraytext := "empty"
    }

    MsgBox, %arraytext%
}

deleteString(string,array){
    if(array.Length() > 0){
        for index,item in array{
            if(item == string){
                array.delete(index)
            }
        }
    }
}

hasValue(haystack, needle) {
    if(!isObject(haystack))
        return false
    if(haystack.Length()==0)
        return false
    for k,v in haystack
        if(v==needle)
        return true
    return false
}

ActivateMButton(){
    global mButtonEnabled
    if(mButtonEnabled){
        mButtonEnabled := 0
        GuiControl,,MBtn,activate Mbutton
    }
    else{
        mButtonEnabled := 1
        GuiControl,,MBtn,disable Mbutton
    }
}

MbuttonFx(){
if (mButtonEnabled)
{
    FecthRandom()
}
else
{
    KeyWait, MButton, D
    Send {MButton down}
    KeyWait MButton
    Send {MButton up}

}

}


MyGuiGuiClose:
ExitApp

^!r::FecthRandom()
^!e::RecoverCrew()
^!Numpad0::SelectTank("KV-2 (R),6,")
^r::Reload
MButton::MbuttonFx()

