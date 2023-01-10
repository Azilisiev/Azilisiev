#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

global fileVar
global imageFolderName := "wallpModding"
global names := ""
global nameListFile := "smartRenameFileNames.txt"
global selectedName := ""

global posy := 50
global posx := 10
global width := 200
global width2 := 50
global height := 50
global maxHeight :=890

SetTitleMatchMode, 2

gui, MyGui:New, , Available names
gui, Font, s15
gui, +LastFound +Resize
Gui, submit,nohide

loadFile()
numNames = 0

Str := ""
loop ,read, %nameListFile%
{
    numNames+=1
    names .= "|" . A_LoopReadLine
}
names := LTrim(names, "|") ; Remove leading pipes (|)

Gui, Add, Button, x%posx% y%posy% h%height% w%width2% gAllMinusButtons vMBtn, -1
posx += width2
Gui, Add, Button, x%posx% y%posy% h%height% w%width2% gAllplusButtons vPBtn, +1
posx += width2
Gui, Add, ComboBox,x%posx% y%posy% h%height% w%width% vCBB, %names%
posx += width
Gui, Add, Button, x%posx% y%posy% h%height% w%width% gOkButton vBtn, OK
Gui, show, h200

OkButton(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := "")
{
    global names
    Gui,1: Submit, NoHide
    GuiControlGet, CBB
    ControlGet, selectedName, Choice,,CBB
    CBB := formatName(CBB)
    changeFileName(imageFolderName,CBB)
    changedName := bumpNameOccurences(CBB,"+",fileVar)
    loadFile()
    reloadList(CBB, changedName)

}

allMinusButtons(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := "")
{
    global names
    Gui,1: Submit, NoHide
    GuiControlGet, CBB
    ControlGet, selectedName, Choice,,CBB
    changedName := bumpNameOccurences(CBB,"-",fileVar)
    names[SubStr(CBB, 4)] := newButtonText
    loadFile()
    reloadList(CBB, changedName)
}

allPlusButtons(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := "")
{
    global names
    Gui,1: Submit, NoHide
    GuiControlGet, CBB
    ControlGet, selectedName, Choice,,CBB
    changedName := bumpNameOccurences(CBB,"+",fileVar)
    names[SubStr(CBB, 4)] := newButtonText
    loadFile()
    reloadList(CBB, changedName)
}

formatName(name){
    intPart :=RegExReplace(name, "^[^0,1,2,3,4,5,6,7,8,9]*|[0,1,2,3,4,5,6,7,8,9]*_", "")
    stringPart :=RegExReplace(name, "[0,1,2,3,4,5,6,7,8,9]{2,3}$", "")
    ;MsgBox, %intPart%
    Length := StrLen(intPart)
    ;MsgBox, %Length%
    if(Length<=1){
        intPart := "01"
        name := stringPart . intpart
        ;MsgBox, newName %name%
    }
    return name
}

changeFileName(folderName,newName){
    IfWinExist,%folderName%
    {
        WinActivate
    }
    Else
    {
        MsgBox, folder not found
    }

    Send, {f2}
    Sleep, 50
    Send, {Raw}%newName%
    Sleep, 50
    Send, {Enter}
}

bumpNameOccurences(name,op,File){
    global nameListFile
    fileContent := File.Read()
    bumpedName := bumpName(name,op)
    ;MsgBox %fileContent%
    If (InStr(fileContent, name)){
        StringReplace, fileContent, fileContent, %name% , %bumpedName%, All
    }
    Else{
        Msgbox 4, Confirm, Add %name% to list?
        IfMsgBox Yes
        fileContent := addName(bumpedName,fileContent)
    }

    ;MsgBox fileContent %fileContent%
    File.Close()
    FileDelete, %nameListFile%
    FileAppend ,%fileContent%,%nameListFile%
    return bumpedName
}

bumpName(name,op){
    intPart :=RegExReplace(name, "^[^0,1,2,3,4,5,6,7,8,9]*|[0,1,2,3,4,5,6,7,8,9]*_", "")
    stringPart :=RegExReplace(name, "[0,1,2,3,4,5,6,7,8,9]{2,3}$", "")
    switch op
    {
    case "+":
    intPart := intPart+1
case "-":
if(intPart+0>=1)
    intPart := intPart-1
}

Length := StrLen(intPart)
if(Length<=1){
    intPart := "0" . intPart
}
;MsgBox StringPart %stringPart%
;MsgBox IntPart %intpart%
newName := stringPart . intpart
;MsgBox, newName %newName%
return newName
}

addName(newName,namelist){
    namelist := namelist . "`n" . newName
    Sort, namelist ,
    Return namelist
}

loadFile(){

    global fileVar
    global nameListFile
    fileVar.Close()
    Try {
        fileVar := FileOpen(nameListFile, "rw")
    }
    Catch e
    {
        MsgBox, An exception was thrown!`nSpecifically: %e%
    }

}

reloadList(oldName, changedName){
    ;MsgBox, %oldName% %changedName%
    global nameListFile
    global names
    changedNames := StrReplace(names, oldName , changedName)
    changedNames := "|" . changedNames
    ;MsgBox, %names% %changedNames%
    Gui, Submit, NoHide
    GuiControlGet, ctrlID, Hwnd ,CBB,
    ;MsgBox, %ctrlID%
    GuiControl,,%ctrlID%, %changedNames%
    GuiControl, ChooseString, %ctrlID%, %changedName%
    GuiControl,Text, %ctrlID%, %changedName%

}

^Numpad1::changeFileName(imageFolderName,"testName3")
^Numpad0::MsgBox % "The result is: " bumpNameOccurences("DITF_02_07","+",fileVar)
^r::Reload

myGuiGuiClose:		;close Gui to Exit
ExitApp

