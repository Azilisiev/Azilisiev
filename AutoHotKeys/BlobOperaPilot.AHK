#NoEnv
#SingleInstance, Force
SendMode, Input
SetBatchLines, -1
SetWorkingDir, %A_ScriptDir%

global xVal := 690
global yVal := 890
global yOffset := 65

global do := 410
global si := 470
global la := 530
global sol := 590
global fa := 650
global mi := 710
global re := 770
global do2 := 830
global si2 := 890 <-
global la2 := 950
global sol2 := 1100

clickStart(){
    global xVal
    global yVal
    MouseClick, left , 690, 890, 1, 0, D
    SetMouseTo(690,890)
}

SetMouseTo(x,y){
    global xVal
    global yVal
    MouseMove, x, y, 0
    xVal := x
    yVal := y
}

music(){
    global xVal
    global yVal
    global do
    global si
    global la
    global sol
    global fa
    global mi
    global re
    global do2
    global si2
    global la2
    global sol2
    clickStart()
    sleep 10
    SetMouseTo(730, yVal)
    SetMouseTo(xVal, do2) ;ti
    sleep 2000
    SetMouseTo(630, yVal)
    SetMouseTo(xVal, re) ;a
    sleep 2000
    SetMouseTo(780, yVal)
    SetMouseTo(xVal, do2) ;mo
    sleep 2000
    SetMouseTo(730, yVal)
    SetMouseTo(xVal, mi) ;in
    sleep 500
    SetMouseTo(780, yVal)
    SetMouseTo(xVal, fa) ;so
    sleep 500
    SetMouseTo(780, yVal)
    SetMouseTo(xVal, mi) ;gno
    sleep 500
    SetMouseTo(730, yVal)
    SetMouseTo(xVal, do2) ;ti
    sleep 2000
    SetMouseTo(630, yVal)
    SetMouseTo(xVal, re) ;a
    sleep 2000
    SetMouseTo(780, yVal)
    SetMouseTo(xVal, do2) ;mo
    sleep 2000
    SetMouseTo(730, yVal)
    SetMouseTo(xVal, sol) ;in
    sleep 500
    SetMouseTo(630, yVal)
    SetMouseTo(xVal, fa) ;a
    sleep 500
    SetMouseTo(630, yVal)
    SetMouseTo(xVal, mi) ;ria
    sleep 500
    SetMouseTo(730, yVal)
    SetMouseTo(xVal, do2) ;ti
    sleep 2000
    SetMouseTo(630, yVal)
    SetMouseTo(xVal, re) ;a
    sleep 2000
    SetMouseTo(780, yVal)
    SetMouseTo(xVal, do2) ;mo
    sleep 2000

}

s::clickStart()
a::SetMouseTo(630, yVal)
e::SetMouseTo(680, yVal)
i::SetMouseTo(730, yVal)
o::SetMouseTo(780, yVal)
u::SetMouseTo(830, yVal)
NumpadAdd::SetMouseTo(xVal, yVal-yOffset)
NumpadSub::SetMouseTo(xVal, yVal+yOffset)
p::MouseClick, left , xVal, yVal, 1, 0, U
c::music()
x::ExitApp, 0
