saveImageAndNext()
{
sendinput {Enter}
sleep 100
sendinput ^+e
sleep 100
MouseClick, left, 64, 480
sleep 100
MouseClick, left, 355, 56
sleep 100
sendinput ^a ;
sendinput ^c ;
sleep 100
Clipboard := StrReplace(Clipboard, "jpg", "png")
sleep 500
sendinput ^v
sleep 100
MouseClick, left, 519, 750
sleep 500
sendinput {Enter}
sleep 100
sendinput !{tab}
sleep 100
MouseClick, left, 470, 200,,,D
MouseClick, left, 380, 214,,10,U
sleep 100
sendinput !{tab}
sleep 2000
MouseClick, left, 350, 474
sleep 100
sendinput !{tab}
sleep 100
MouseClick, left, 508, 200,2
sleep 100
sendinput !{tab}
sleep 100
MouseMove, 792, 195


}

MButton::saveImageAndNext()