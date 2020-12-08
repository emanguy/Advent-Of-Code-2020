package lib

enum class Operation {
    NO_OP,
    JUMP,
    ACCUMULATOR_ADD,
    ;

    companion object {
        fun fromString(opStr: String): Operation = when(opStr) {
            "nop" -> NO_OP
            "jmp" -> JUMP
            "acc" -> ACCUMULATOR_ADD
            else -> throw IllegalArgumentException("Invalid opperation: $opStr")
        }
    }
}
data class Instruction(val op: Operation, val argument: Long) {
    companion object {
        fun fromString(instructionStr: String): Instruction {
            val (stringOp, stringArg) = instructionStr.split(" ")
            return Instruction(Operation.fromString(stringOp), stringArg.toLong())
        }
    }
}
data class ConsoleState(val instructionIdx: Int = 0, val accRegister: Long = 0)

fun consoleCompile(instructions: List<String>): List<Instruction> = instructions.map { Instruction.fromString(it) }
fun consoleExecStep(instructions: List<Instruction>, state: ConsoleState): ConsoleState {
    if (state.instructionIdx !in instructions.indices) throw IllegalArgumentException("Instruction index outside program range: ${state.instructionIdx}")

    val currentInstruction = instructions[state.instructionIdx]
    return when (currentInstruction.op) {
        Operation.NO_OP -> state.copy(instructionIdx = state.instructionIdx + 1)
        Operation.JUMP -> state.copy(instructionIdx = state.instructionIdx + currentInstruction.argument.toInt())
        Operation.ACCUMULATOR_ADD -> state.copy(instructionIdx = state.instructionIdx + 1, accRegister = state.accRegister + currentInstruction.argument)
    }
}

