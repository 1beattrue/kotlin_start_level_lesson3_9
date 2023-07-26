import kotlin.math.sqrt

fun main() {
    // extension функции


    val age = 5

    if (age in 12..100) {
        println("valid")
    }
    // ...
    if (age in 12..100) {
        println("valid2")
    }
    // ...

    // мы хотим изменить условие сразу во всех местах программы
    // конечно, мы можем написать функцию fun isValid(age: Int) = age in 12..100, но ниже способ 2

    if (age.isValue()) { // пример не самый удачный, ниже пример покрасочнее
        println("valid")
    }

    val n = 0
    println(n.isPositive()) // проверка на положительность числа

    println()

    val prime = 13
    val notPrime = 98
    println(prime.isPrime())
    println(notPrime.isPrime())

    val list = listOf<Int>()
    myWith(list) {
        println(this) // мы можем обращаться здесь к объекту list через this, так как данная функция расширяет класс List
    }

    val string = "hello"
    myWith(string) {
        println(this.toUpperCase())
    }

    val map = mutableMapOf<Int, String>()
    myWithGeneric(map) {
        keys
        values
        // ... крч все работает
    }
}

// в котлине мы можем расширить любой класс, добавив собственные методы
fun Int.isValue() = this in 12..100

fun Int.isPositive() = this > 0

// задача
fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    if (this <= 3) return true

    for (d in 2..sqrt(this.toDouble()).toInt() + 1) {
        if (this % d == 0) {
            return false
        }
    }

    return true
}

// реализация функции myWith
fun myWith(list: List<Int>, operation: List<Int>.() -> Unit) { /* таким образом в качестве второго параметра имеем
                                                                  extension функцию, расширяющую класс List<Int> */
    list.operation()
}

fun myWith(string: String, operation: String.() -> Unit) { // перегруженный метод для строк
    string.operation()
}

fun myWithAny(obj: Any, operation: Any.() -> Unit) {
    obj.operation()
} /* универсальный метод для всех типов объектов,
     но тоже не очень хорошо, так как теперь
     у объекта есть только три метода */

inline fun <T> myWithGeneric(obj: T, operation: T.() -> Unit) {
    obj.operation()
} // generic функция (общего вида)

// если хотим добавить еще и возвращаемое значение:
inline fun <T, R> myWithGeneric2(obj: T, operation: T.() -> R) : R { // по факту with так и реализована:)
    return obj.operation()
}