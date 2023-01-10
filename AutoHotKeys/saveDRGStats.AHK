readScreen(x1,y1,x2,y2)
{
MouseMove,%x1% , %y1%
sendinput ^q
Sleep 200
MouseClick, left, %x2%, %y2%
sleep 100
}

readScreenRelative(x1,y1,length,height,color)
{
PixelSearch, x1, y1, x1, y1, x1+length, y1+length, color,1, Fast
if ErrorLevel
    MsgBox, That color was not found in the specified region.
else
	{
	y1 := y1-10
    MouseMove, %x1%,%y1%
	sleep 20
	}

sendinput ^q
Sleep 200
x1 := x1+100
y1 := y1+30
MouseClick, left, %x1%, %y1%
sleep 100

}

SearchColor(x1,y1,length,color)
{
PixelGetColor, pixelColor, %x1%, %y1%
while(color != pixelColor && length>0)
	{
	x1++
	length--
	PixelGetColor, pixelColor, %x1%, %y1%

	}
if (length<=0)
	{
	return -1
	}
else
	{
	return x1
	}
}

write(text,x,y)
{
MouseClick, left, %x%, %y%, 1
clipBoard := %text%
sendinput ^v
sendinput !{TAB}
sleep 100
Mousemove, 962, 777
}

saveDRGStats(x1,y1,x2,y2,x3,y3){
readScreen(x1 , y1, x2 , y2)
MouseClick, left, 90, 50
sendinput ^a
sendinput ^c
MouseClick, left, 324, 181
MouseClick, left, %x3%, %y3%, 1
sleep 100
StringReplace, clipboard, clipboard, l, 1, All
StringReplace, clipboard, clipboard, I, 1, All
StringReplace, clipboard, clipboard, B, 8, All
StringReplace, clipboard, clipboard, El, 0, All
StringReplace, clipboard, clipboard, E], 0, All
StringReplace, clipboard, clipboard, E1, 0, All
StringReplace, clipboard, clipboard, BG, 00, All
StringReplace, clipboard, clipboard, |, 1, All
StringReplace, clipboard, clipboard, f1, 0, All
sleep 100
sendinput ^v
sendinput !{TAB}
}

saveDRGStatsText(x1,y1,x2,y2,x3,y3){
readScreen(x1 , y1, x2 , y2)
MouseClick, left, 90, 50
sendinput ^a
sendinput ^c
MouseClick, left, 324, 181
MouseClick, left, %x3%, %y3%, 1
clipboard :=RegExReplace(clipboard, ".N-SITE .EFINING", "ON-SITE REFINING")
clipboard :=RegExReplace(clipboard, "S.L.*GE .PER.TI.N", "SALVAGE OPERATION")
clipboard :=RegExReplace(clipboard, "E.* HUNT", "EGG HUNT")
clipboard :=RegExReplace(clipboard, "MINING EXPEDITI.N","MINING EXPEDITION")
clipboard :=RegExReplace(clipboard, "POINT EXT.*CTI.N", "POINT EXTRACTION")
clipboard :=RegExReplace(clipboard, ".LIMIN.*TI.N", "ELIMINATION")
clipboard :=RegExReplace(clipboard, "Tyrant Sh.*", "Tyrant Shard")
clipboard :=RegExReplace(clipboard, "INDUSTRI.L S.B.T.GE","INDUSTRIAL SABOTAGE")zzz
sleep 100
sendinput ^v
sendinput !{TAB}
}

saveDRGStatsRelative(x1,y1,lenght,height,color,x3,y3){
readScreenRelative(x1,y1,lenght,height, color)
MouseClick, left, 90, 50
sendinput ^a
sendinput ^c
MouseClick, left, 324, 181
MouseClick, left, %x3%, %y3%, 1
sleep 100
StringReplace, clipboard, clipboard, 8, 0, All
StringReplace, clipboard, clipboard, [, 1, All
clipboard :=RegExReplace(clipboard, " ","")

sleep 100
sendinput ^v
sendinput !{TAB}

}


saveAllnoEvent(){
verifyBlank(1662, 219)
saveDRGStatsText(125, 720, 450, 740,2015,211) ;type
sleep 100
saveDRGStatsRelative(800, 716, 150, 0, 0xFFFFFF, 2115, 211) ;diff
sleep 100
write("0",2215,211) ;event
sleep 100
saveDRGStats(1781,698,1853,734,2315,211) ;time
sleep 100
saveDRGStats(190,759,353,811,2415,211) ;total cred
sleep 100
saveDRGStats(341, 817,401, 837,2510,211) ;primary
sleep 100
saveDRGStats(341,837,401,862,2610,211) ;secondary
sleep 100
write("0",2710,211) ;event
sleep 100
saveDRGStats(341,862,401,890,2810,211) ;survival
sleep 100
saveDRGStats(341,890,401,911,2910,211) ;gold
sleep 100
saveDRGStats(1511,763,1706,807,3010,211) ;total xp
sleep 100
saveDRGStats(1688, 820,1748, 837,3110,211) ;primary
sleep 100
saveDRGStats(1688,840,1748,865,3210,211) ;secondary
sleep 100
write("0",3310,211) ;event
sleep 100
saveDRGStats(1688,865,1748,890,3410,211) ;minerals
sleep 100
saveDRGStats(1688,890,1748,915,3510,211) ;kills

}

saveAll(){
verifyBlank(1662, 219)
saveDRGStatsText(125, 720, 450, 740,2015,211) ;type
sleep 100
saveDRGStatsRelative(800, 716, 150, 0, 0xFFFFFF, 2115, 211) ;diff
sleep 100
saveDRGStatsText(135, 866, 360, 889,2215,211) ;event
sleep 100
saveDRGStats(1781,698,1853,734,2315,211) ;time
sleep 100
saveDRGStats(190,759,353,811,2415,211) ;total cred
sleep 100
saveDRGStats(350,816,430,837,2510,211) ;primary
sleep 100
saveDRGStats(350,837,430,862,2610,211) ;secondary
sleep 100
saveDRGStats(350,862,430,890,2710,211) ;event
sleep 100
saveDRGStats(350,890,430,911,2810,211) ;survival
sleep 100
saveDRGStats(350,911,430,938,2910,211) ;gold
sleep 100
saveDRGStats(1511,763,1706,807,3010,211) ;total xp
sleep 100
saveDRGStats(1688,817,1760,840,3110,211) ;primary
sleep 100
saveDRGStats(1688,840,1760,865,3210,211) ;secondary
sleep 100
saveDRGStats(1688,865,1760,890,3310,211) ;event
sleep 100
saveDRGStats(1688,890,1760,915,3410,211) ;minerals
sleep 100
saveDRGStats(1688,915,1760,940,3510,211) ;kills

}

saveAllDataCell(){
verifyBlank(1662, 219)
saveDRGStatsText(125, 720, 450, 740,2015,211) ;type
sleep 100
saveDRGStatsRelative(800, 716, 150, 0, 0xFFFFFF, 2115, 211) ;diff
sleep 100
saveDRGStatsText(190, 917, 260, 939,2215,211) ;event
sleep 100
saveDRGStats(1781,698,1853,734,2315,211) ;time
sleep 100
saveDRGStats(190,759,353,811,2415,211) ;total cred
sleep 100
saveDRGStats(341, 817,401, 837,2510,211) ;primary
sleep 100
saveDRGStats(341,837,401,862,2610,211) ;secondary
sleep 100
saveDRGStats(341,862,401,890,2810,211) ;survival
sleep 100
saveDRGStats(341,890,401,911,2910,211) ;gold
sleep 100
saveDRGStats(341,911,401,938,2710,211) ;event
sleep 100
saveDRGStats(1511,763,1706,807,3010,211) ;total xp
sleep 100
saveDRGStats(1691,817,1765,840,3110,211) ;primary
sleep 100
saveDRGStats(1691,840,1765,865,3210,211) ;secondary
sleep 100
saveDRGStats(1691,865,1765,890,3310,211) ;event
sleep 100
saveDRGStats(1691,890,1765,915,3410,211) ;minerals
sleep 100
saveDRGStats(1691,915,1765,940,3510,211) ;kills

}

verifyBlank(x1,y1)
{
if WinExist("DRGStats.ods - LibreOffice Calc")
	{
	WinActivate
	;MsgBox Calc found
	}
else
	{
	RunWait, open "D:\divers Jeux\DRGStats.ods"
	
	}

PixelGetColor, color, %x1%, %y1%
while (color != 0xFFFFFF )
	{
	Mousemove, x1, y1
	send {WheelDown}
	sleep 50
	PixelGetColor, color, %x1%, %y1%
	;MsgBox The color at %x1% , %y1% current cursor position is %color%
	}

if WinExist("Deep Rock Galactic")
	{
	WinActivate
	;MsgBox DRG found
	}
else
	{
	MsgBox no DRG found
	}
}

^Numpad5::saveAll()
^Numpad6::saveAllnoEvent()
^Numpad7::saveAllDataCell()
^Numpad1::verifyBlank(1662, 219)
^Numpad2::readScreenRelative(847, 716 ,200, 50, 0xFFFFFF)
^Numpad8::
^t::saveDRGStatsText(125, 720, 335, 740,2015,211)
^r::Reload