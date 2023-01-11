package twohundred.operator

class IndexedAccessOperator(var name:String, var birthday:String) {
    operator fun get(position:Int):String {
        return when(position) {
            0 -> name
            1 -> birthday
            else -> "none"
        }
    }

    operator fun set(position:Int, value:String) {
        when(position) {
            0 -> name = value
            1 -> birthday = value
        }
    }


}