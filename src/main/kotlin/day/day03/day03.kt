package day.day03

import helpers.ReadFile

// This code is copied - couldn't do this one! But wanted something to come back to at some point.

fun main() {
    partOne()
}

private fun partOne() {
    val lines = ReadFile("day03.txt").getListOfStrings()

    val grid = Grid(lines)
    val result = getNumbersAndNeighbours(grid)
        .filter { (_, neighbours) -> neighbours.any { grid.get(it).isSymbol() } }
        .sumOf { it.first }

    println(result)
}

fun getNumbersAndNeighbours(grid: Grid): List<Pair<Int, List<Pair<Int, Int>>>> {
    val numbersAndNeighbours = mutableListOf<Pair<Int, List<Point>>>()
    (0..grid.maxY).forEach { y ->
        (0..grid.maxX).forEach { x ->
            if (grid.get(x, y).isDigit() && !grid.get(x - 1, y).isDigit()) {
                var i = x
                val digits = mutableListOf<Char>()
                while (grid.get(i, y).isDigit()) {
                    digits.add(grid.get(i, y))
                    i++
                }
                val number = digits.joinToString("").toInt()
                val neighbours = ((x - 1)..i).toList()
                    .flatMap { listOf(Pair(it, y - 1), Pair(it, y + 1)) } +
                        Pair(x - 1, y) +
                        Pair(i, y)
                numbersAndNeighbours.add(Pair(number, neighbours))
            }
        }
    }
    return numbersAndNeighbours
}

private fun Char.isSymbol() = !this.isDigit() && this != '.'

class Grid(private val input: List<String>) {
    fun get(point: Point): Char =
        this.get(point.first, point.second)

    fun get(x: Int, y: Int): Char {
        if (y < 0 || y >= input.size) return '.'
        val row = input[y]
        if (x < 0 || x >= row.length) return '.'
        return row[x]
    }

    val maxX: Int
        get() = input[0].length - 1
    val maxY: Int
        get() = input.size - 1
}

typealias Point = Pair<Int, Int>