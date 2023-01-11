package twohundred.operator

class InvokeOperator(val id:Int, val name:String) {
    operator fun invoke(value:Int) {
        println(value)
        println("id : $id\nname : $name")
    }

    operator fun invoke(value:Int, s:String) {
        println(value)
        println(s)
        println("id : $id\nname : $name")
    }
}