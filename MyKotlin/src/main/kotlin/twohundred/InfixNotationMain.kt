package twohundred

class InfixNotationMain(var x:Int = 0, var y:Int = 0) {
    infix fun from(base:InfixNotationMain):InfixNotationMain {
        println("x : $x, y : $y")
        return InfixNotationMain(x - base.x, y - base.y)
    }
}

fun main() {
    //중위 표기법(Infix Notation)
    //val pt = InfixNotationMain(3, 6).from(InfixNotationMain(1, 1))의 다른 표현
    val pt = InfixNotationMain(3, 6) from InfixNotationMain(1, 1)
    println(pt.x)
    println(pt.y)
}