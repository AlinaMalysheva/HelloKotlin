package ru.netology

import allowToPay
import calcCommission
import org.junit.Test

import org.junit.Assert.*
import toPay

class RemittanceKtTest {

    @Test
    fun calcCommissionZeroForMasterCard() {
        val minCommissionMCard = 20
        val percentCommissionMCard = 0.006

        val cardType = "MasterCard"
        val amount = 74_000

        val result = calcCommission(amount, cardType)
        assertEquals(0, result)
    }

    @Test
    fun calcCommissionForMasterCard() {
        val minCommissionMCard = 20
        val percentCommissionMCard = 0.006

        val cardType = "MasterCard"
        val amount = 76_000

        val result = calcCommission(amount, cardType)
        assertEquals(26, result)
    }

    @Test
    fun calcCommissionForMaestro() {
        val cardType = "Maestro"
        val amount = 76_000
        val result = calcCommission(amount, cardType)
        assertEquals(26, result)
    }

    @Test
    fun calcCommissionZeroForMaestro() {
        val cardType = "MasterCard"
        val amount = 74_000
        val result = calcCommission(amount, cardType)
        assertEquals(0, result)
    }

    @Test
    fun calcCommissionMinForVisa() {
        val cardType = "Visa"
        val amount = 30
        val result = calcCommission(amount, cardType)
        assertEquals(35, result)
    }

    @Test
    fun calcCommissionMinForMir() {
        val cardType = "Mir"
        val amount = 30
        val result = calcCommission(amount, cardType)
        assertEquals(35, result)
    }

    @Test
    fun calcCommissionForVisa() {
        val cardType = "Visa"
        val amount = 4800
        val result = calcCommission(amount, cardType)
        assertEquals(36, result)
    }

    @Test
    fun calcCommissionForMir() {
        val cardType = "Mir"
        val amount = 4800
        val result = calcCommission(amount, cardType)
        assertEquals(36, result)
    }

    @Test
    fun calcCommissionForVKPay() {
        val cardType = "VKPay"
        val amount = 4800
        val result = calcCommission(amount, cardType)
        assertEquals(0, result)
    }

    @Test
    fun allowToPayLimit() {
        val amount = 50_000
        val remitancePrivMonthAmount = 550_000
        val remitancePrivDayAmount = 100_000
        val result = allowToPay(amount, remitancePrivMonthAmount,remitancePrivDayAmount)
        assertEquals(true, result)
    }

    @Test
    fun notAllowToPayMonthLimit() {
        val amount = 50_000
        val remitancePrivMonthAmount = 550_001
        val remitancePrivDayAmount = 100_000
        val result = allowToPay(amount, remitancePrivMonthAmount,remitancePrivDayAmount)
        assertEquals(false, result)
    }

    @Test
    fun notAllowToPayDayLimit() {
        val amount = 50_000
        val remitancePrivMonthAmount = 550_000
        val remitancePrivDayAmount = 100_001
        val result = allowToPay(amount, remitancePrivMonthAmount,remitancePrivDayAmount)
        assertEquals(false, result)
    }

    @Test
    fun toPay() {
        val amount = 50_000
        val cardType = "smth"
        val result = toPay(cardType, amount)
        assertEquals(50_000, result)
    }

    @Test
    fun toPayWithCommision() {
        val amount = 50
        val cardType = "Mir"
        val result = toPay( cardType, amount,)
        assertEquals(85, result)
    }

}

/*
fun toPay(cardType: String, remitancePrivMonthAmount: Int, amount: Int): Int {
        return (amount + calcCommission(amount, cardType))
    }
}*/