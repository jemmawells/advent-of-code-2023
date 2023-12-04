package day.day01

import helpers.ReadFile

private val alphaRegex = "[a-zA-Z]".toRegex()

fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val lines = ReadFile("day01.txt").getListOfStrings()

    val combinedFirstAndLastDigitsSum = lines.sumOf { line ->
        val numbersOnly = line.replace(alphaRegex, "")
        "${numbersOnly.first()}${numbersOnly.last()}".toInt()
    }

    println(combinedFirstAndLastDigitsSum)
}

private fun partTwo() {
    val lines = ReadFile("day01.txt").getListOfStrings()

    val numberMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    val reversedNumberMap = mapOf(
        "one".reversed() to "1",
        "two".reversed() to "2",
        "three".reversed() to "3",
        "four".reversed() to "4",
        "five".reversed() to "5",
        "six".reversed() to "6",
        "seven".reversed() to "7",
        "eight".reversed() to "8",
        "nine".reversed() to "9"
    )

    val wordList = numberMap.map { it.key }
    val backwardsWordList = reversedNumberMap.map { it.key }
    val numberList = numberMap.map { it.value }
    val combinedListForwards = wordList + numberList
    val combinedListBackwards = backwardsWordList + numberList

    val total = lines.sumOf { line ->
        val resultGoingForwards = findNumbersAndAddToList(line, combinedListForwards).map { it.replaceNumbers(numberMap) }
        val resultGoingBackwards = findNumbersAndAddToList(line.reversed(), combinedListBackwards).map { it.replaceNumbers(reversedNumberMap) }

        "${resultGoingForwards.first()}${resultGoingBackwards.first()}".toInt()
    }

    println(total)
}

private fun findNumbersAndAddToList(line: String, substrings: List<String>): MutableList<String> {
    val overlappingSubstrings = mutableListOf<String>()
    val pattern = Regex(substrings.joinToString("|"))

    pattern.findAll(line).forEach { matchResult:MatchResult ->
        overlappingSubstrings.add(matchResult.value)
    }

    return overlappingSubstrings
}

private fun String.replaceNumbers(numberMap: Map<String, String>): String {
    return numberMap.entries.fold(this) { acc, (key, value) ->
        acc.replace(key, value)
    }
}