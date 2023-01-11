package twohundred.inheritance

open class ExtendsClass(val name:String, val age:Int) {
    open fun print() {
        println("이름 : $name")
        println("나이 : $age")
    }
}

fun main() {
    //클래스 없이 객체를 만들 때 쓰는 object 표현식으로 상속을 할 수 있다.
    //1회용 상속
    val custom:ExtendsClass = object:ExtendsClass("Alan", 23) {
        override fun print() {
            println("It's a object")
        }
    }
    custom.print()
}

