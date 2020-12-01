import java.io.File

fun main() {
    val numbers = File("inputs/official/1-1-report-repair.txt").readLines().map { it.toInt() }
    // Keep track of numbers we'd need to form 2020
    val oppositeSet = mutableSetOf<Int>()

    for (number in numbers) {
        if (number !in oppositeSet) {
            oppositeSet += (2020 - number)
        } else {
            val multTotal = number * (2020 - number)
            println("Got it! $number * ${2020 - number} = $multTotal")
            return
        }
    }

    println("Couldn't find a match.")
    println("Opposite set: $oppositeSet")
}