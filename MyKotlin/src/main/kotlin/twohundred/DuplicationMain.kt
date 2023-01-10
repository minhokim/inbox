package twohundred

class DuplicationMain {
    var num = 15

    fun memberFunc(num:Int) {
        println(num)
        println(this.num)
    }
}

fun main() {
    val a = DuplicationMain()
    a.memberFunc(53)
}