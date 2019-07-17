@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import com.sun.org.apache.xpath.internal.operations.And
import kotlinx.html.attributes.stringSetDecode
import lesson1.task1.accountInThreeYears
import lesson1.task1.sqr
import lesson5.task1.propagateHandshakes
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int =
        when {
            n < 10 -> 1
            else -> digitNumber(n / 10) + 1
        }


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    return if ((n == 1) or (n == 2)) 1
// Решение при помощи цикла
/*
    else {
        var x1 = 1
        var x2 = 1
        var y: Int = 0
        for (m: Int in 3..n) {
            y = x1 + x2
            x2 = x1
            x1 = y
        }
        return y
    }
*/
// Решение при помощи рекурсии
    else fib(n - 1) + fib(n - 2)
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {

    for (i in 1..n) {
        var ym = m * i
        for (j in 1..m){
            var yn = j * n
            if (ym == yn) return ym
        }
    }
    return 0
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n) {
        if (n % i == 0) return i
    }
    return n
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in (n - 1) downTo 2) {
        if (n % i == 0) return i
    }
    return 1
}


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..m)
        if (m % i == 0)
            for (j in 2..n)
                if ((n % j == 0) and (j == i)) return false
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n)
        if (sqrt(i.toDouble()) - round(sqrt(i.toDouble())) == 0.0) return true
    return false
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var y = x
    var count: Int = 0
    while (y != 1) {
        if (y % 2 == 0) y /= 2
        else y = 3 * y + 1
        ++count
    }
    return count

}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var y : Double = 0.0
    var b : Int = 0
    var xx = if (abs(x) <= PI * 2) x else x % (PI * 2)
    var member = xx
    while (eps < abs(member)) {
        y += member
        b++
        member = (if (b % 2 == 0) 1 else -1) * exp((2 * b + 1) * ln(xx)) / factorial(2 * b + 1)

    }
    return y

}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var y: Double = 0.0
    var b: Int = 0
    var xx = if (abs(x) <= PI * 2) x else x % (PI * 2)
    var member = 1.0
    while (eps < abs(member)) {
        y += member
        b++
        member = (if (b % 2 == 0) 1 else -1) * exp((2 * b) * ln(xx)) / factorial(2 * b)
    }

    return y

}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var nn = n
    var y: Int = 0
    while (nn > 0) {
        var z = nn / 10
        y = nn - z * 10 + 10 * y
        nn = z
    }
    return y
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var nn = n
    var y: Int = 0
    while (nn > 0) {
        var z = nn / 10
        y = nn - z * 10 + 10 * y
        nn = z
    }
    return y == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1: Int
    var n2: Int
    var i = n
    var nn: Int
    while (i / 10 > 0) {
        nn = i / 10
        n1 = i - nn * 10
        n2 = nn - nn / 10 * 10
        i = nn
        if (n1 != n2) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var i: Int = 1
    var x: Int = 1
    var y: Int
    var nn: Int
    while (i <= n) {
        nn = sqr(x)
        x++
        y = 0
        var j: Int = 0
        while (nn > 0) {                           // Переворачиваем  число ПРИМер 16 -> 61
            var z = nn / 10
            y = nn - z * 10 + 10 * y
            nn = z
            j++
        }
        while (j > 0) {                            //Выискиваем число
            var m = y - y / 10 * 10
            y /= 10
            if (i == n) return m
            i++
            j--
        }

    }
    return 0
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var i: Int = 1
    var x: Int = 1
    var y: Int
    var nn: Int
    while (i <= n) {
        nn = fib(x)
        x++
        y = 0
        var j: Int = 0
        while (nn > 0) {                           // Переворачиваем  число ПРИМер 16 -> 61
            var z = nn / 10
            y = nn - z * 10 + 10 * y
            nn = z
            j++
        }
        while (j > 0) {                            //Выискиваем число
            var m = y - y / 10 * 10
            y /= 10
            if (i == n) return m
            i++
            j--
        }

    }
    return 0
}