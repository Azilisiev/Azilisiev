#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%
;Shift + Alt + F 
;variables
;calc
firstCellCoords := [75, 215]
cellDistances := [82, 15]

typeCell := firstCell

;drg
blankCellColor = 0xFFFFFF
textColor = 0xFFFFFF
maxDarkPixelThreshold = 20

missionTypeCoords1 := [125, 720]
missionTypeCoords2 := [335, 740]
missionTypeCell = "a"

difficultyCoords1 := [574, 697]
difficultyCoords2 := [961, 740]
difficultyLength = 150
difficultyCell = "b"

missionTime1 := [1781, 699]
missionTime2 :=[1855, 738]
missionTimeCell = "d"

totalCreditsCoords1 := [146, 764]
totalCreditsCoords2 := [407,811]
totalCreditsCell = "e"

detailCreditsCoords1 := [156, 814]
detailCreditsCoords2 := [453, 1019]

eventTypeCell = "c"
primaryObjectiveCreditsCell = "f"
secondaryObjectiveCreditsCell = "g"
eventCreditsCell = "h"
survivalBonusCell = "i"
mineralsCreditsCell = "j"

totalXpCoords1 := [1511,763]
totalXpCoords2 := [1706,807]

detailXpCoords1 := [1471, 809]
detailXpCoords2 := [1810,961]

;functions
saveStats(){

    global missionTypeCoords1
    global missionTypeCoords2
    missionType = % readScreen(missionTypeCoords1[1],missionTypeCoords1[2],missionTypeCoords2[1],missionTypeCoords2[2])

    missionType :=RegExReplace(missionType, ".N-SITE .EFINING", "ON-SITE REFINING")
    missionType :=RegExReplace(missionType, "S.L.*GE OPER.TI.N", "SALVAGE OPERATION")
    missionType :=RegExReplace(missionType, "E.* HUNT", "EGG HUNT")
    missionType :=RegExReplace(missionType, "POINT EXT.*CTI.N", "POINT EXTRACTION")
    missionType :=RegExReplace(missionType, ".LIMIN.*ITION", "ELIMINATION")
    missionType :=RegExReplace(missionType, "ESC.*DUTY", "ESCORT DUTY")

    global difficultyCoords1
    global difficultyCoords2
    difficulty = % readScreen(difficultyCoords1[1],difficultyCoords1[2],difficultyCoords2[1],difficultyCoords2[2])

    difficulty := RegExReplace(difficulty, ".*\+", "+")
    difficulty := RegExReplace(difficulty, "8", "0")

    global missionTime1
    global missionTime2
    missionTime = % readScreen(missionTime1[1],missionTime1[2],missionTime2[1],missionTime2[2])

    missionTime := convertToNumber(missionTime)

    global totalCreditsCoords1
    global totalCreditsCoords2
    totalCredits = % readScreen(totalCreditsCoords1[1],totalCreditsCoords1[2],totalCreditsCoords2[1],totalCreditsCoords2[2])

    totalCredits := convertToNumber(totalCredits)

    global detailCreditsCoords1
    global detailCreditsCoords2
    numberOfCreditsLines = % numberOfRows(detailCreditsCoords1[1],detailCreditsCoords1[2], detailCreditsCoords2[1],detailCreditsCoords2[2])

    global totalXpCoords1
    global totalXpCoords2
    totalXP = % readScreen(totalXpCoords1[1],totalXpCoords1[2],totalXpCoords2[1],totalXpCoords2[2])

    global detailXpCoords1
    global detailXpCoords2
    XPlines = % numberOfRows(detailXpCoords1[1],detailXpCoords1[2],detailXpCoords2[1],detailXpCoords2[2])

    primaryObjectiveCredits = ""
    secondaryObjectiveCredits = ""
    eventCredits = ""
    eventType = ""
    dataCell = ""
    bitterGem = ""
    survivalBonus = ""
    mineralsMined = ""
    switch numberOfCreditsLines
    {
    case 3:
        primaryObjectiveCredits = % readScreen(160,813,427,843)

        secondaryObjectiveCredits = % readScreen(160, 841, 425,866)

        eventCredits = 0
        eventType = 0
        dataCell = 0
        bitterGem = 0

        survivalBonus = % readScreen(160, 869, 427,897)

        mineralsMined = 0

    case 4:
        primaryObjectiveCredits = % readScreen(160,813,427,843)

        secondaryObjectiveCredits = % readScreen(160, 841, 427,866)

        eventCredits = 0
        eventType = 0
        dataCell = 0
        bitterGem = 0

        survivalBonus = % readScreen(160, 869, 427,897)

        mineralsMined = % readScreen(160, 887, 427,918)

    }
    primaryObjectiveCredits := convertToNumber(primaryObjectiveCredits)

    secondaryObjectiveCredits := convertToNumber(secondaryObjectiveCredits)

    survivalBonus := convertToNumber(survivalBonus)

    
    ;MsgBox, type: %missionType% diff: %difficulty% time: %missionTime% credsline: %numberOfLines% creds: %totalCredits% xp: %totalXP%
}
numberOfRows(x1,y1,x2,y2){
    readScreen(x1,y1,x2,y2)
    StringReplace Clipboard,Clipboard,`n,`n,UseErrorLevel
    return ErrorLevel + 1
}

convertToNumber(string){
    num := RegExReplace(string,"[E,f][l,\],1]","0")
    /*
    MsgBox %num%
    sleep 200
    */
    num := RegExReplace(num,"[1,2,3,4,5,6,7,8,9,0, ]+[l,I,\|]|[l,I,\|][1,2,3,4,5,6,7,8,9,0,]+","1")
    /*
    MsgBox %num%
    sleep 200
    */
    num := RegExReplace(num,".* [x,X]|^[^1,2,3,4,5,6,7,8,9,0]*|[^1,2,3,4,5,6,7,8,9,0].","")
    /*
    MsgBox %num%
    sleep 200
    */
    num := RegExReplace(num,"[ ,©]*","")
    /*
    MsgBox %num%
    sleep 200
    */
    num := num*1
    return num
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

mouveTo(x1,y1)
{
    MouseMove,%x1% , %y1%, 0
}

letterToNum(letter)
{
    StringLower letter, letter
    return Asc(letter) - 97
}

verifyBlank(x1,y1)
{
    if WinExist("DRGStats.ods - LibreOffice Calc")
    {
        WinActivate
    }
    else
    {
        MsgBox no calc found
    }

    PixelGetColor, color, %x1%, %y1%
    while (color != blankCellColor )
    {
        Mousemove, x1, y1
        send {WheelDown}
        sleep 50
        PixelGetColor, color, %x1%, %y1%
    }

    if WinExist("Deep Rock Galactic")
    {
        WinActivate
    }
    else
    {
        MsgBox no DRG found
    }
}
getBoxCoords(){
    KeyWait, LButton, D
    MouseGetPos,x1, y1
    KeyWait, LButton, U
    KeyWait, LButton, D
    MouseGetPos,x2, y2
    clipboard := x1 ", " y1 ", " x2 "," y2
    MsgBox %Clipboard%
}

;hotkeys
Numpad5::saveStats()
Numpad4::MsgBox, % convertToNumber("0 x Survival Bonus 0©")
^Numpad0::getBoxCoords()
^r::Reload
