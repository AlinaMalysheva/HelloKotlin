fun main() {
    var regularCustomer = false
    var summOrder = 100
    var toPay: Int


    if (summOrder > 10_000) {
        toPay = summOrder - (summOrder * 0.05).toInt()
    } else if (summOrder > 1_000) {
        toPay = summOrder - 100
    } else {
        toPay = summOrder
    }
    if (regularCustomer) {
        toPay = toPay - (toPay * 0.01).toInt()
    }

    println("Вы впервые за последний месяц заказали наши товары на сумму $summOrder. К оплате $toPay ")

    regularCustomer = false
    summOrder = 1001

    if (summOrder > 10_000) {
        toPay = summOrder - (summOrder * 0.05).toInt()
    } else if (summOrder > 1_000) {
        toPay = summOrder - 100
    } else {
        toPay = summOrder
    }
    if (regularCustomer) {
        toPay = toPay - (toPay * 0.01).toInt()
    }
    println("Вы впервые за последний месяц заказали наши товары на сумму $summOrder. К оплате $toPay ")

    regularCustomer = false
    summOrder = 10_001

    if (summOrder > 10_000) {
        toPay = summOrder - (summOrder * 0.05).toInt()
    } else if (summOrder > 1_000) {
        toPay = summOrder - 100
    } else {
        toPay = summOrder
    }
    if (regularCustomer) {
        toPay = toPay - (toPay * 0.01).toInt()
    }
    println("Вы впервые за последний месяц заказали наши товары на сумму $summOrder. К оплате $toPay ")

    regularCustomer = true
    summOrder = 100

    if (summOrder > 10_000) {
        toPay = summOrder - (summOrder * 0.05).toInt()
    } else if (summOrder > 1_000) {
        toPay = summOrder - 100
    } else {
        toPay = summOrder
    }
    if (regularCustomer) {
        toPay = toPay - (toPay * 0.01).toInt()
    }
    println("Вы наш постоянный клиент! Товары на сумму $summOrder. К оплате $toPay ")

    regularCustomer = true
    summOrder = 1001

    if (summOrder > 10_000) {
        toPay = summOrder - (summOrder * 0.05).toInt()
    } else if (summOrder > 1_000) {
        toPay = summOrder - 100
    } else {
        toPay = summOrder
    }
    if (regularCustomer) {
        toPay = toPay - (toPay * 0.01).toInt()
    }
    println("Вы наш постоянный клиент! Товары на сумму $summOrder. К оплате $toPay ")
    regularCustomer = true
    summOrder = 10001

    if (summOrder > 10_000) {
        toPay = summOrder - (summOrder * 0.05).toInt()
    } else if (summOrder > 1_000) {
        toPay = summOrder - 100
    } else {
        toPay = summOrder
    }
    if (regularCustomer) {
        toPay = toPay - (toPay * 0.01).toInt()
    }
    println("Вы наш постоянный клиент! Товары на сумму $summOrder. К оплате $toPay ")
}
/* Задание3
Условия акции
Если сумма покупки от 0 до 1 000 рублей, то скидка не предоставляется.
Если сумма покупки от 1 001 до 10 000 рублей, то скидка составит 100 рублей (как в лекции).
Если сумма покупки от 10 001 рубля и выше, то скидка составит 5% от суммы.
Все цены указаны в рублях.

При этом постоянные пользователи, то есть те, кто покупает ежемесячно, дополнительно получают 1% скидки сверху.

Важно: скидки не суммируются, а применяются сверху. Например, у пользователя скидка 5%, значит 1% применяется к 95%:*/
