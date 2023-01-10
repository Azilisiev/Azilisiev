#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

SetTitleMatchMode, 2

cutAndSavePng(quit){
    Send {Enter}
    Sleep, 50
    Sendinput ^+e
    WinWait, Exporter
    Sleep, 50
    Send ^a
    Send ^c
    Sleep, 50
    Clipboard := StrReplace(Clipboard, "jpg", "png")
    Send ^v
    Sleep, 100
    MouseClick, Left, 50, 456
    Sleep, 50
    MouseClick, Left, 712, 749
    WinWait, PNG
    Sleep, 50
    MouseClick, Left, 351, 471

    if(quit){
        WinWait, wallpModding
        WinActivate
        Send {Right}
        Sleep, 200
        Send {Enter}
        WinWait, GIMP
        WinActivate
        WinWaitClose, Exporter
        Sleep, 600
        closeFirstTab()
    }

}
closeFirstTab(){
    Sleep, 200
    Sendinput !{1}
    Sleep, 200
    Sendinput ^w
    Sleep, 200
    Sendinput {Left}
    Sleep, 200
    Sendinput {Enter}
}

^Numpad1::closeFirstTab()
^Numpad0::cutAndSavePng(1)
^Numpad2::cutAndSavePng(0)
MButton::cutAndSavePng(1)
^r::Reload