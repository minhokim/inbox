package twohundred

class TwoHundred {
}

fun doubleCheck() {
    val num: Int = 3
    val dNum:Double = num.toDouble() + 2
    //println(dNum)
}

fun inDeOperator() {
    println("----- inDeOperator -----")
    var a = 10
    var b = 5

    println(a++ + b)
    println(a)
    println(--b)
    println()
}

fun bitOperator() {
    println("----- bitOperator -----")
    println(15 and 7)   //15와 7을 비트 단위로 and 연산
    println(5 or 2)     //5와 2를 비트 단위로 or 연산
    println(15 xor 5)   //15와 5를 비트 단위로 xor 연산
    println(32767.inv())    //32767을 비트 단위로 반전
    println(1 shl 3)    //1을 왼쪽으로 3칸 시프트
    println(8 shr 1)    //8을 오른쪽으로 1칸 시프트
    println(-17 ushr 2) //부호를 유지한채 -17을 오른쪽으로 2칸 시프트
    println()
}

fun expressionInfoString() {
    println("----- expressionInfoString -----")
    val a = 10
    val b = 20

    println("a의 값: \$a")
    println("b의 값: $b")

    println("a + b = $a + $b")
    println("a + b = ${a + b}")
    println()
}

fun flowWhen() {
    println("----- flowWhen -----")
    val score = 34

    when (score / 10)
    {
        6 -> println('D')
        7 -> println('C')
        8 -> println('B')
        9, 10 -> println('A')
        else -> println('F')
    }
    println()
}

fun expWhen() {
    println("----- expWhen -----")
    val score = 95

    val grade:Char = when (score / 10)
    {
        6 -> 'D'
        7 -> 'C'
        8 -> 'B'
        9,10 -> 'A'
        else -> 'F'
    }

    println("grade : $grade")
    println()
}

fun ifElseWhen() {
    println("----- ifElseWhen -----")
    val score = 95

    val grade:Char = when
    {
        score >= 90 -> 'A'
        score >= 80 -> 'B'
        score >= 70 -> 'C'
        score >= 60 -> 'D'
        else -> 'F'
    }

    println("grade : $grade")
    println()
}

fun testWhile() {
    println("----- testWhile -----")
    var i = 1

    while(i < 10) {
        println(i)
        i ++
    }
    println()
}

fun testDoWhile() {
    println("----- testDoWhile -----")
    var i =1
    do {
        println("do while")
    } while(i == 0)
    println()
}

fun exContinue() {
    println("----- exContinue -----")
    var i = 0
    while (i < 10) {
        i ++
        if(i % 2 == 0)
            continue
        print(i)
    }
    println()
}

fun exBreak() {
    println("----- exBreak -----")
    var i = 0
    while (true) {
        i ++
        if(i >= 5)
            break
        print(i)
    }
    println()
}

//레이블에 해당하는 반복문을 빠져나온다.
fun exLabel() {
    println("----- exLabel -----")
    var x = 0
    var y = 0

    outer@while(x <= 20) {
        y = 0
        while(y <= 20) {
            if (x + y == 15 && x - y == 5)
                break@outer
            y++
        }
        x++
    }
    println("x: $x, y: $y")
    println()
}

fun myFun(): Double {
    println("----- myFun -----")
    val a = 3.5
    val b = 6
    println("a: $a, b: $b")
    return a + b
}

fun myFun2(): Double = 3.5 + 7

fun myFun3() = 3.5 + 7

fun getAvg(a: Int, b: Int): Double {
    println("----- getAvg -----")
    return (a + b) / 2.0
}

fun main(args: Array<String>): Unit {
    doubleCheck()
    inDeOperator()
    bitOperator()
    expressionInfoString()
    flowWhen()
    expWhen()
    ifElseWhen()
    testWhile()
    testDoWhile()
    exContinue()
    exBreak()
    exLabel()
    myFun()
    println("myFun2: "+ myFun2())
    println("myFun3: " + myFun3())
    println("getAvg: " + getAvg(3, 7))
}