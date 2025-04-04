import kotlin.math.sqrt

fun main() {

}

fun isValidSudoku(sudokuBoard: Array<Array<String>>): Boolean {
    if (sudokuBoard.isEmpty()) return false

    if (!sudokuBoard.size.isPerfectSquare()
        && sudokuBoard.size % 2 != 0
        && sudokuBoard.size != 3
    ) {
        return false
    }

    val allowedRange = 1..sudokuBoard.size

    sudokuBoard.forEach { row ->
        if (row.size != sudokuBoard.size) {
            //Check if the board is prefect Square (Columns = Rows, ex: 3x3, 9x9)
            return false
        }

        row.forEach { cell ->
            val isValidSudokuCell = cell.isValidSudokuCell(allowedRange)
            if (!isValidSudokuCell) return false

            if (row.toList().hasRepeatedNumbers()) {
                //Check if row has duplicate cells
                return false
            }
        }
    }


    val columns = Array(sudokuBoard.size) { i ->
        Array(sudokuBoard.size) { j -> sudokuBoard[j][i] }
    }

    columns.forEach { column ->
        if (column.toList().hasRepeatedNumbers()) {
            //Check if column has duplicate cells
            return false
        }
    }

    val grids = extractSubGrids(sudokuBoard)
    grids.forEach { grid ->
        if (grid.hasRepeatedNumbers()) {
            //Check if grid has duplicate cells
            return false
        }
    }

    return true
}

fun extractSubGrids(grid: Array<Array<String>>): List<List<String>> {
    val gridSize = grid.size
    val (subGridRow, subGridColumn) = getSubGridRowColumn(gridSize)

    val subGrids = mutableListOf<List<String>>()

    for (rowStart in 0..<gridSize step subGridRow) {
        for (colStart in 0..<gridSize step subGridColumn) {
            val subgrid = mutableListOf<String>()

            for (row in rowStart..<rowStart + subGridRow) {
                for (col in colStart..<colStart + subGridColumn) {
                    subgrid.add(grid[row][col])
                }
            }
            subGrids.add(subgrid)
        }
    }
    return subGrids
}

fun getSubGridRowColumn(gridSize: Int): Pair<Int, Int> {
    var subGridRow = 0
    var subGridColumn = 0
    if (gridSize.isPerfectSquare()) {
        val sqrtValue = sqrt(gridSize.toDouble()).toInt()
        subGridRow = sqrtValue
        subGridColumn = sqrtValue
    } else {
        //10x10, 6x6
        for (i in 2..gridSize) {
            if (gridSize % i == 0) {
                subGridRow = i
                subGridColumn = gridSize / subGridRow
                break
            }
        }
    }
    return Pair(subGridRow, subGridColumn)
}

fun Int.isPerfectSquare(): Boolean {
    if (this < 0) return false // Negative numbers can't be perfect squares
    val sqrtValue = sqrt(this.toDouble()).toInt()
    return sqrtValue * sqrtValue == this
}

fun <T> Collection<T>.hasRepeatedNumbers(): Boolean {
    val emptyCell = "-"
    val rowWithoutEmptyChars = this.filter { it != emptyCell }
    return rowWithoutEmptyChars.distinct().size != rowWithoutEmptyChars.size
}

fun String.isValidSudokuCell(allowedRange: IntRange): Boolean {
    val emptyChar = '-'
    if (this.singleOrNull() == emptyChar) {
        return true
    }

    val number = this.toIntOrNull() ?: return false

    return number in allowedRange
}