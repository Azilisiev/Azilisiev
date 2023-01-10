#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

global fileVar
global imageFolderName := "wallpModding"
global names := []
global nameListFile := "smartRenameFileNames.txt"

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
loop ,read, %nameListFile%
{
    numNames+=1
    names.Push(A_LoopReadLine)
    Gui, Add, Button, x%posx% y%posy% h%height% w%width2% gAllMinusButtons vMBtn%A_Index%, -1
    posx += width2
    Gui, Add, Button, x%posx% y%posy% h%height% w%width2% gAllplusButtons vPBtn%A_Index%, +1
    posx += width2
    Gui, Add, Button, x%posx% y%posy% h%height% w%width% gAllButtons vBtn%A_Index%, % A_LoopReadLine
    posx -= width2*2
    if(posy>maxHeight){
        posy = 50
        posx += width + width2*2 + 10
    }
    else{
        posy += height + 5
    }
    
}

    Gui, Add, Button, x5 y5 gReloadNames vReload, Reload names

    gui, show

    ReloadNames(){
        Reload
    }

    allButtons(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := "")
    {
        global names
        Gui Submit, NoHide
        GuiControlGet bttnid, Name, % CtrlHwnd
        GuiControlGet,name,,%bttnid%
        changeFileName(imageFolderName,name)
        newButtonText := bumpNameOccurences(name,"+",fileVar)
        loadFile() 
        GuiControl,,%bttnid%, %newButtonText%
    }

    allMinusButtons(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := "")
    {
        global names
        Gui Submit, NoHide
        GuiControlGet bttnid, Name, % CtrlHwnd
        bttnid := RegExReplace(bttnid, "M", "")
        GuiControlGet,name,,%bttnid%
        newButtonText := bumpNameOccurences(name,"-",fileVar)
        names[SubStr(name, 4)] := newButtonText
        loadFile()
        GuiControl,,%bttnid%, %newButtonText%
    }

    allPlusButtons(CtrlHwnd, GuiEvent, EventInfo, ErrLevel := "")
    {
        global names
        Gui Submit, NoHide
        GuiControlGet bttnid, Name, % CtrlHwnd
        bttnid := RegExReplace(bttnid, "P", "")
        GuiControlGet,name,,%bttnid%
        newButtonText := bumpNameOccurences(name,"+",fileVar)
        names[SubStr(name, 4)] := newButtonText
        loadFile()
        GuiControl,,%bttnid%, %newButtonText%
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
        fileContent := File.Read()
        bumpedName := bumpName(name,op)
        ;MsgBox %bumpedName%
        StringReplace, fileContent, fileContent, %name% , %bumpedName%, All
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

    loadFile(){
        global fileVar
        fileVar.Close()
        Try {
            fileVar := FileOpen(nameListFile, "rw")
        }
        Catch e
        {
            MsgBox, An exception was thrown!`nSpecifically: %e%
        }
    }

    ^Numpad1::changeFileName(imageFolderName,"testName3")
    ^Numpad0::MsgBox % "The result is: " bumpNameOccurences("DITF_02_07","+",fileVar)
    ^r::Reload