#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

Toggle := 0

RButton::
Global Toggle := !Toggle
Loop, 100000
{
	If (!Toggle)
		Break
	Click
	Sleep 1 ; Make this number higher for slower clicks, lower for faster.
}


p::
Suspend,Toggle
r::
Reload
x::
ExitApp
