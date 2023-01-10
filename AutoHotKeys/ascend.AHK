#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

SetTitleMatchMode, 2

upgrade(){
    Loop 21
    {
        MouseClick, Left, 1801, 234
        MouseMove, 1607, 368
        sleep 100
        MouseClick, Left, 1856, 323
        MouseMove, 1607, 368
        sleep 100
        MouseClick, Left, 1816, 356
        sleep 500
    }
}

fractals(){
    MouseClick, WheelDown,1770, 386,20
    sleep 160
    MouseClick, Left, 1798, 895,3,0
}

legacy(){
    MouseClick, Left, 1613, 168
    sleep 200
    MouseClick, Left, 958, 639
}

reincarnate(){
    MouseClick, Left, 963, 209
    sleep 200
    MouseClick, Left, 939, 605

}

cycle(){
    sleep 300
    upgrade()
    sleep 200
    fractals()
    sleep 850
    legacy()
    sleep 5200
    reincarnate()
}

doit(){
    Loop 900
    {
        cycle()
        sleep 400
    }
}

Numpad0::upgrade()
Numpad1::fractals()
Numpad2::legacy()
Numpad3::reincarnate()
Numpad4::cycle()
Numpad5::doit()
x::ExitApp,0
