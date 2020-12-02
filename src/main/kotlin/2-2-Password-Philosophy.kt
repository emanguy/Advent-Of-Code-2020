import lib.PasswordPolicy
import java.io.File

fun main() {
    val puzzleInputs = File("inputs/official/2-1-password-philosophy.txt").readLines()

    var validPasswords = 0
    for (input in puzzleInputs) {
        val (rulePart, rawPasswordPart) = input.split(":")
        val passwordPart = rawPasswordPart.trim()
        val rule = PasswordPolicy.fromRule(rulePart)

        val charIndexRange = 1..passwordPart.length
        if (rule.range.first !in charIndexRange || rule.range.last !in charIndexRange) continue
        if ((passwordPart[rule.range.first-1] == rule.character) xor (passwordPart[rule.range.last-1] == rule.character)) {
            validPasswords++
        }
    }

    println("There are $validPasswords valid passwords.")
}