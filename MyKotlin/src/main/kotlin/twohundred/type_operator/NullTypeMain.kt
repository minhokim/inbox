package twohundred.type_operator

import twohundred.inheritance.Person

class NullTypeMain {
}

fun main() {
    //타입 이름 뒤에 ?를 붙이면 변수를 Nullable하게 만들 수 있다.
    var person:Person? = Person("K", 30)
    person = null

    //num은 Nullable 타입이므로 스택이 아닌 힙에 실 데이터가 들어간다.
    var num:Int? = null
    num = 10

    var str:String? = if(true) "Test" else null
}

