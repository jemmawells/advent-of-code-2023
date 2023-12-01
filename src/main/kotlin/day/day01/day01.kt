package day.day01

import helpers.ReadFile

fun main() {
    partOne()
}

fun partOne() {
    val lines = ReadFile("day01.txt").getListOfStrings()
    val alphaRegex = "[a-zA-Z]".toRegex()

    val combinedFirstAndLastDigitsSum = lines.sumOf { line ->
        val numbersOnly = line.replace(alphaRegex, "")
        "${numbersOnly.first()}${numbersOnly.last()}".toInt()
    }

    println(combinedFirstAndLastDigitsSum)
}
