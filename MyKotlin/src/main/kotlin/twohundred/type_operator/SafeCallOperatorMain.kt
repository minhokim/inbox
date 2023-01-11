package twohundred.type_operator

import twohundred.Building

class SafeCallOperatorMain {
}

fun main() {
    var obj:Building? = null

    //참조 변수가 null일 때 멤버 함수를 호출하지 않으며, 참조 변수?.멤버 함수() 표현식은 null이 된다.
    obj?.print()
    obj?.name = "건물"

    obj = Building()
    obj?.name = "경기장"
    obj?.date = "2023-01-11"
    obj?.area = 21_222
    obj?.print()

}
