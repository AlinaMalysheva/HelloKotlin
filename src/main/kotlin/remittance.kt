fun main() {
    val amount = 140_000
    val cardType = "Maestro"
    var remitancePrivMonthAmount = 0
    val remitancePrivDayAmount = 0

    if (allowToPay(amount,remitancePrivDayAmount, remitancePrivMonthAmount)) {
        val resultSumm = toPay(cardType, remitancePrivMonthAmount, amount)
        println("На данный момент Вы перевели $remitancePrivMonthAmount. Для перевода $amount c $cardType внесите: ${resultSumm.toInt()} рублей.")
    } else {
        println("Вы превысили один из лимитов, перевод невозможен. За день c этимпереводом получается ($remitancePrivDayAmount+$amount) - лимит 150_000. " +
                "За месяц - $remitancePrivMonthAmount. Лимит 600_000 ")
    }
}

fun allowToPay(amount: Int, remitancePrivMonthAmount: Int, remitancePrivDayAmount: Int): Boolean {
    return ( amount+ remitancePrivDayAmount < 150_001 && amount+ remitancePrivMonthAmount < 600_001)
}

fun toPay(cardType: String, remitancePrivMonthAmount: Int, amount: Int): Int {
        return (amount + calcCommission(amount, cardType))
    }


fun calcCommission(amount: Int, cardType: String): Int {
    val minCommissionMCard = 20
    val percentCommissionMCard = 0.006
    val minCommissionVisa = 35
    val percentCommissionVisa = 0.0075

    if (cardType == "MasterCard") {
        val amountUnderCommision = amount - 75_000
        if (amountUnderCommision > 0) {
            return ((amountUnderCommision * percentCommissionMCard) + minCommissionMCard).toInt()
        } else{
            return 0
        }
    }
    if (cardType == "Maestro") {
        return (if (amount * percentCommissionVisa < minCommissionVisa) minCommissionVisa else (amount * percentCommissionVisa).toInt())
    } else {
        return 0
    }
}

/*
За переводы с карты Mastercard комиссия не взимается, пока не превышен месячный лимит в 75 000 руб.
Если лимит превышен, комиссия составит 0,6% + 20 руб.
За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.
За переводы с карты Мир комиссия не взимается.
Кроме того, введём лимиты на суммы перевода за сутки и за месяц. Максимальная сумма перевода с одной карты:

150 000 руб. в сутки
600 000 руб. в месяц
Комиссия в лимитах не учитывается.
 */

