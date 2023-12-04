package day.day02

import helpers.ReadFile

val numberColourRegex = "(\\d+) (blue|green|red)".toRegex()
const val maxRed = 12
const val maxGreen = 13
const val maxBlue = 14

fun main() {
    partOne()
}

private fun partOne() {

    val lines = ReadFile("day02.txt").getListOfStrings()
    val possibleGames = mutableListOf<Int>()

    lines.map { line ->

        val gameNumber = line.substringBefore(":").replace("Game ", "").toInt()

        val match = numberColourRegex.findAll(line).toList()

        val anyImpossibleResults = match.map {
            when {
                (it.groupValues[2] == "blue" && it.groupValues[1].toInt() > maxBlue) -> true
                (it.groupValues[2] == "red" && it.groupValues[1].toInt() > maxRed) -> true
                (it.groupValues[2] == "green" && it.groupValues[1].toInt() > maxGreen) -> true
                else -> false
            }
        }
        if (!anyImpossibleResults.contains(true)) possibleGames.add(gameNumber)
    }

    println(possibleGames.sum())
}