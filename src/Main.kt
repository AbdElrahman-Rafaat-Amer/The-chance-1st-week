
fun main() {

}

fun isValidSudoku(sudokuBoard: Array<Array<String>>): Boolean {
    if (sudokuBoard.isEmpty()) return false

    val allowedRange = 1..sudokuBoard.size

    sudokuBoard.forEach { row ->
        if (row.size != sudokuBoard.size) {
            //Check if the board is prefect Square (Columns = Rows, ex: 3x3, 9x9)
            return false
        }

        row.forEach { cell ->
            val isValidSudokuCell = cell.isValidSudokuCell(allowedRange)
            if (!isValidSudokuCell) return false

            if (hasRepeatedNumbers(row)) {
                //Check if row has duplicate cells
                return false
            }
        }
    }


    val columns = Array(sudokuBoard.size) { i ->
        Array(sudokuBoard.size) { j -> sudokuBoard[j][i] }
    }

    columns.forEach { column ->
        if (hasRepeatedNumbers(column)) {
            //Check if column has duplicate cells
            return false
        }
    }

    return true
}

fun hasRepeatedNumbers(row: Array<String>) : Boolean{
    val emptyCell = "-"
    val rowWithoutEmptyChars = row.filter { it !=  emptyCell}
    return rowWithoutEmptyChars.distinct() .size != rowWithoutEmptyChars.size
}

fun String.isValidSudokuCell(allowedRange: IntRange): Boolean {
    val emptyChar = '-'
    if (this.singleOrNull() == emptyChar) {
        return true
    }

    val number = this.toIntOrNull() ?: return false

    return number in allowedRange
}