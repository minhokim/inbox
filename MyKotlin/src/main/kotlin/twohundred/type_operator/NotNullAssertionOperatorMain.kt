package twohundred.type_operator

import twohundred.Building

class NotNullAssertionOperatorMain {
}

fun main() {
    var obj:Building? = Building()

    // !! 연산자는 Nullable 타입을 Not-null 타입으로 강제 캐스팅한다.
    obj!!.name = "서울시청"
    println(obj!!.name)

    obj = null
//    obj!!.print()
}

