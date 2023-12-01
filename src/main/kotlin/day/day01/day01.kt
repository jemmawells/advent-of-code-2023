package day.day01

import helpers.ReadFile

val alphaRegex = "[a-zA-Z]".toRegex()

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val lines = ReadFile("day01.txt").getListOfStrings()

    val combinedFirstAndLastDigitsSum = lines.sumOf { line ->
        val numbersOnly = line.replace(alphaRegex, "")
        "${numbersOnly.first()}${numbersOnly.last()}".toInt()
    }

    println(combinedFirstAndLastDigitsSum)
}

fun partTwo() {
    val lines = ReadFile("day01.txt").getListOfStrings()

    val numbersPairs = listOf(
        Pair("one", "1"),
        Pair("two", "2"),
        Pair("three", "3"),
        Pair("four", "4"),
        Pair("five", "5"),
        Pair("six", "6"),
        Pair("seven", "7"),
        Pair("eight", "8"),
        Pair("nine", "9")
    )

    val wordList = numbersPairs.map { it.first }
    val backwardsWordList = wordList.map { it.reversed() }
    val numberList = numbersPairs.map { it.second }
    val combinedListForwards = wordList + numberList
    val combinedListBackwards = backwardsWordList + numberList

    val total = lines.sumOf { line ->
        val resultGoingForwards = findNumbersAndAddToList(line, combinedListForwards).map { it.replaceNumbers() }
        val resultGoingBackwards = findNumbersAndAddToList(line.reversed(), combinedListBackwards).map { it.replaceBackwardsNumbers() }

        val firstDigit = resultGoingForwards.first().toInt()
        val lastDigit = resultGoingBackwards.first().toInt()

        firstDigit.times(10).plus(lastDigit)
    }

    println(total)
}

fun findNumbersAndAddToList(line: String, substrings: List<String>): MutableList<String> {
    val overlappingSubstrings = mutableListOf<String>()
    val pattern = Regex(substrings.joinToString("|"))

    pattern.findAll(line).forEach { matchResult:MatchResult ->
        overlappingSubstrings.add(matchResult.value)
    }

    return overlappingSubstrings
}

fun String.replaceNumbers() =
    this.replace("one", "1")
        .replace("two", "2")
        .replace("three", "3")
        .replace("four", "4")
        .replace("five", "5")
        .replace("six", "6")
        .replace("seven", "7")
        .replace("eight", "8")
        .replace("nine", "9")

fun String.replaceBackwardsNumbers() =
    this.replace("one".reversed(), "1")
        .replace("two".reversed(), "2")
        .replace("three".reversed(), "3")
        .replace("four".reversed(), "4")
        .replace("five".reversed(), "5")
        .replace("six".reversed(), "6")
        .replace("seven".reversed(), "7")
        .replace("eight".reversed(), "8")
        .replace("nine".reversed(), "9")