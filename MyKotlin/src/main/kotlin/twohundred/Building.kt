package twohundred

class Building {
    var name = ""
    var date = ""
    var area = 0

    //멤버 함수
    fun print() {
        println("이름:" + this.name)
        println("건축일자: ${this.date}")
        println("면적: ${this.area}")
    }
}