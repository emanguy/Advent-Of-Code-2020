import java.io.File

fun main() {
    val numbers = File("inputs/official/1-1-report-repair.txt").readLines().map { it.toInt() }

    // This is a really gross solution and I hate it but w/e
    for (outerIndex in 0..(numbers.lastIndex - 2)) {
        val firstNumber = numbers[outerIndex]

        for (middleIndex in outerIndex..(numbers.lastIndex - 1)) {
            val secondNumber = numbers[middleIndex]
            // Quick shortcut. Don't need to find a third number if the first and second gets us too large.
            if (firstNumber + secondNumber >= 2020) continue

            for (lastIndex in middleIndex..numbers.lastIndex) {
                val thirdNumber = numbers[lastIndex]

                if (firstNumber + secondNumber + thirdNumber == 2020) {
                    val result = firstNumber * secondNumber * thirdNumber
                    println("Found it! $firstNumber * $secondNumber * $thirdNumber = $result")
                    return
                }
            }
        }
    }

    println("Couldn't find the numbers.")
}