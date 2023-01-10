#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

getBoxCoords(){
    KeyWait, LButton, D
    MouseGetPos,x1, y1
    KeyWait, LButton, U
    KeyWait, LButton, D
    MouseGetPos,x2, y2
    clipboard := x1 ", " y1 ", " x2 "," y2
    MsgBox %Clipboard%
}

readScreen(x1,y1,x2,y2)
{
    Clipboard = ""
    MouseMove,%x1%, %y1%, 0
    Sleep 300
    SendInput ^q
    Sleep 300
    MouseMove,%x2%, %y2%, 0
    sleep 200
    Send {LButton}
    sleep 200
    Send {Escape}
    return Clipboard
}

getTankName(){
    While(!GetKeyState("s","P")){
        if WinExist("Client de WoT")
        {
            WinActivate
        }
        else
        {
            MsgBox WOT not found
            Return 0
        }
        tankName := readScreen(612, 198, 718, 222)
        if(!GetKeyState("s","P")){
            sleep 50
            send {WheelDown}
            sleep 50
            tankName :=RegExReplace(tankName, "P.\.", "Pz.")
            tankName :=RegExReplace(tankName, " lll", "III")
            tankName :=RegExReplace(tankName, " ll", "II")
            tankName :=RegExReplace(tankName, " l[V,v]","IV")
            WinActivate, ahk_class Notepad
            Send, {PgDn}
            sleep 50
            Send, {Enter}
            Send, %tankName%
            if WinExist("Client de WoT")
            {
                WinActivate
            }
        }

    }
}

;hotkeys
^Numpad1::getTankName()
^Numpad0::getBoxCoords()
^r::Reload