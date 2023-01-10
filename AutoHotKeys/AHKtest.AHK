#SingleInstance, Force
SendMode Input
SetWorkingDir, %A_ScriptDir%

liste := ["ah!","heh?"]
if(hasValueWaffenträger(liste,"ah!")){
    MsgBox, hasVal
}

hasValue(haystack, needle) {
    if(!isObject(haystack))
        return false
    if(haystack.Length()==0)
        return false
    for k,v in haystack
        if(v==needle)
            return true
    return false
}
SendInput, Waffentr{U+00E4}ger