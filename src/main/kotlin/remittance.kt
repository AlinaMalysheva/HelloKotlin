fun main() {
    var amount = 20000
    var cardType = "MasterCard"
    var remitancePrivAmount = 250

    var resultSumm = toPay(cardType, remitancePrivAmount, amount)

    println("На данный момент Вы перевели $remitancePrivAmount. Для перевода $amount c $cardType внесите: ${resultSumm.toInt()} рублей.")
}

fun toPay(cardType: String, remitancePrivAmount: Int, amount: Int): Int {
    if ((cardType == "MasterCard" || cardType == "Maestro") && remitancePrivAmount < 301 || cardType == "VKPay") {
        return amount
    } else {
        return (amount + calcCommission(amount))
    }
}

fun calcCommission(amount: Int): Int {
    val minCommission = 35
    val percentCommission = 0.0075
    return (if (amount * percentCommission < minCommission) minCommission else (amount * percentCommission).toInt())
}

/*В прошлый раз мы рассматривали упрощённый вариант вычисления комиссии. Давайте усложним задачу.

За MasterCard и Maestro вообще не нужно платить, пока не превысили лимит (замечание от 300 убираем), а для VK Pay всегда бесплатно:



Напишите алгоритм расчёта в виде функции, передавая в функцию:

тип карты/счёта (по умолчанию VK Pay);
сумму предыдущих переводов в этом месяце (по умолчанию 0 рублей);
сумму совершаемого перевода.
Итог: у вас должен быть репозиторий на GitHub, в котором будет ваш Gradle-проект.*/

/**
 * Задание 1.
 *  Представим, что за переводы с любых карт комиссия составляет 0.75 %, минимум 35 рублей.
Что нужно сделать: напишите небольшую программу, в которой в переменной amount хранится сумма перевода в рублях.
Ваше приложение должно высчитывать комиссию, которую заплатит пользователь при переводе. Комиссия должна быть в рублях.
Итог: у вас должен быть репозиторий на GitHub, в котором расположен ваш Gradle-проект.
 */

