package twohundred.constructor

class SecondaryConstructorMain(val second:Int) {
    init {
        println("init")
    }

    //보조 생성자1
    constructor(min:Int, second:Int):this(min * 60 + second) {
        println("Second Constructor1")
    }

    //보조 생성자2
    constructor(hour:Int, min:Int, second: Int):this(hour * 60 + min, second) {
        println("Second Constructor2")
    }

    init {
        println("another init")
    }
}

fun main() {
    println("${SecondaryConstructorMain(15, 6).second} 초")
    println("${SecondaryConstructorMain(6, 3, 17).second} 초")
}