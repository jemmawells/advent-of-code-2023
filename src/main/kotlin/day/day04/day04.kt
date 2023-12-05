package day.day04

import helpers.ReadFile
import kotlin.math.pow

fun main() {
    partOne()
}

private fun partOne() {
    val lines = ReadFile("day04.txt").getListOfStrings()

    val winningScratchcardTotals = mutableListOf<Int>()

    lines.map { line ->
        val splitLine = line.split(":", "|")

        val cardNumber = splitLine[0].replace("Card   ", "").replace("Card  ", "").replace("Card ", "").toInt()
        val winningNumbers = splitLine[1].trim().replace("  ", " ").split(" ")
        val myNumbers = splitLine[2].trim().replace("  ", " ").split(" ")

        val scratchcard = Scratchcard(
            cardNumber = cardNumber,
            winningNumbers = winningNumbers,
            myNumbers = myNumbers
        )

        var winningNumberTally = 0

        scratchcard.myNumbers.map {
            if (winningNumbers.contains(it)) winningNumberTally += 1
        }

        if (winningNumberTally != 0) {
            val total = 2.toDouble().pow(winningNumberTally - 1)
            winningScratchcardTotals.add(total.toInt())
        }

    }
    println(winningScratchcardTotals.sum())
}

data class Scratchcard(
    val cardNumber: Int,
    val winningNumbers: List<String>,
    val myNumbers: List<String>
)
