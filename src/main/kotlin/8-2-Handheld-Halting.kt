import lib.*
import java.io.File

fun main() {
    val instructions = consoleCompile(File("inputs/official/8-1-handheld-halting.txt").readLines())

    for (index in instructions.indices) {
        val modifiedInstructions: List<Instruction> = when (instructions[index].op) {
            Operation.ACCUMULATOR_ADD -> continue
            Operation.JUMP -> {
                val listCopy = instructions.toMutableList()
                listCopy[index] = instructions[index].copy(op = Operation.NO_OP)

                listCopy
            }
            Operation.NO_OP -> {
                val listCopy = instructions.toMutableList()
                listCopy[index] = instructions[index].copy(op = Operation.JUMP)

                listCopy
            }
        }

        val previouslyExecutedInstructions = mutableSetOf<Int>()
        var currentState = ConsoleState()

        while (true) {
            previouslyExecutedInstructions += currentState.instructionIdx
            val nextState = consoleExecStep(modifiedInstructions, currentState)

            if (nextState.instructionIdx in previouslyExecutedInstructions) break
            if (nextState.instructionIdx == modifiedInstructions.size) {
                println("Got it: changing instruction ${instructions[index]} to ${modifiedInstructions[index]} @ idx $index results in run-to-completion w/ acc ${nextState.accRegister}")
                return
            }

            currentState = nextState
        }
    }

    println("Couldn't find instruction...")
}