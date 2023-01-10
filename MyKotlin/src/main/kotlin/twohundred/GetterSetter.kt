package twohundred

class GetterSetter {
    var age:Int = 0
        get() {
            println("--- get ---")
            return field
        }
        set(value) {
            println("--- set ---")
            field = if(value >= 0) value else 0
        }

    val isYoung get() = age < 30

}