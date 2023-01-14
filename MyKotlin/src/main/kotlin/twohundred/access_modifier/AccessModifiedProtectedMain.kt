package twohundred.access_modifier

class AccessModifiedProtectedMain {
}

//protected 클래스와 서브클래스에서만 접근 가능
open class AAA(protected val number:Int)

class BBB(number:Int):AAA(number) {
    fun printNumber() {
        //protected로 지정되어 있기 때문에 접근 가능
        println(number)
    }
}

fun main() {
    val test = BBB(36)
//    println(test.number)  //에러
    test.printNumber()
}