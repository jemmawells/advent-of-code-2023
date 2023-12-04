package day.day03

import helpers.ReadFile


fun main() {
    partOne()
}

private fun partOne() {
    val line = ReadFile("day03Example.txt").getString()

    TODO()
}

private fun Char.isSymbol() = !this.isDigit() && this != '.'