package twohundred.access_modifier

class ExtensionFunctionMain {
}

fun String.isNumber(): Boolean {
    var i = 0
    while(i < this.length) {
        //숫자가 아닌 문자가 하나라도 들어있으면 false 반환
//        if(!(this[i] in '0'..'9'))
        /*if(!('0' <= this[i] && this[i] <= '9'))
            return false*/

        if(this[i] in '0'..'9') {
            i++
        } else {
            return false
        }
//        i++
    }
    return true
}

fun main() {
    println("1234567890".isNumber())
    println("500 원".isNumber())
}