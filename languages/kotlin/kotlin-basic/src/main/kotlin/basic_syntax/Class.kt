package basic_syntax

//class Shape  // 일반적으로 final 클래스로 선언됨
open class Shape  // open 선언해줘야 상속 가능

class Rectangle(var height: Double, var length: Double): Shape() {
    var perimeter = (height + length) * 2
}

fun main() {
    val rectangle = Rectangle(5.0, 2.0)
    println("Perimeter: ${rectangle.perimeter}")
}