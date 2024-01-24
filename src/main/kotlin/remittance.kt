fun main() {
    var amount = 20000
    val minCommission = 35
    val percentCommission = 0.0075

    var resultCommission = if (amount * percentCommission < minCommission) minCommission else (amount * percentCommission).toInt()

    println("Для перевода $amount комиссия составит: ${resultCommission.toInt()} рублей")

    amount = 200
    resultCommission = if (amount * percentCommission < minCommission) minCommission else (amount * percentCommission).toInt()

    println("Для перевода $amount комиссия составит: ${resultCommission.toInt()} рублей")

    /**
     * Задание 1.
     *  Представим, что за переводы с любых карт комиссия составляет 0.75 %, минимум 35 рублей.
    Что нужно сделать: напишите небольшую программу, в которой в переменной amount хранится сумма перевода в рублях.
    Ваше приложение должно высчитывать комиссию, которую заплатит пользователь при переводе. Комиссия должна быть в рублях.
    Итог: у вас должен быть репозиторий на GitHub, в котором расположен ваш Gradle-проект.
     */

}