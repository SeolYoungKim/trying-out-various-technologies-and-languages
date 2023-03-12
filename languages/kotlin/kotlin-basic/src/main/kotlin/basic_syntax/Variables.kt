package basic_syntax

val PI = 3.14
var x = 0

fun incrementX() {
    x += 1
}

fun main() {
    // 읽기 전용 지역변수 "val"
    // 한 번만 할당될 수 있음. java의 final 역할을 하는 듯?
    val a: Int = 1
    val b = 2
    val c: Int
    c = 3

    // 재할당 가능한 변수 "var"
    var y = 5
    y += 1

    incrementX()
    println(x)
}