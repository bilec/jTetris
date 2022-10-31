package game

import game.tetrominoes.Tetromino
import gui.label.InfoLabel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.config.GameMatrix
import utils.config.NumberOfTicksBeforeNewTetromino
import utils.random.randomTetromino
import java.awt.Color

object Game : KoinComponent {
    private val gameMatrix: GameMatrix by inject()
    private val numberOfTicksBeforeNewTetromino: NumberOfTicksBeforeNewTetromino by inject()
    private val infoLabel: InfoLabel by inject()

    val gameBoard = Array(gameMatrix.width) { arrayOfNulls<Color>(gameMatrix.height) }

    var isRunning = false
    var score = 0

    var currentTetromino: Tetromino = randomTetromino()
    private var cannotMoveDownNum = 0
    var rotationsMade = 0

    fun startNewGame() {
        gameBoard.indices.forEach { i ->
            gameBoard[i].fill(null, 0, gameBoard[i].size)
        }

        currentTetromino = randomTetromino()
        cannotMoveDownNum = 0
        rotationsMade = 0

        score = 0
        isRunning = true

        infoLabel.setScore(score)
    }

    private fun cannotMoveDown() {
        val currentRotation = currentTetromino.currentRotation()

        currentRotation.indices.forEach { i ->
            currentRotation[i].indices.forEach { j ->
                if (currentRotation[i][j] == 1) {
                    val x = j + currentTetromino.point.x
                    val y = i + currentTetromino.point.y
                    gameBoard[x][y] = currentTetromino.color
                }
            }
        }

        val deletableTableRows = findDeletableTableRows()
        if (deletableTableRows.isNotEmpty()) {
            deleteTableRows(deletableTableRows)
            score += (gameMatrix.width * deletableTableRows.size)
            infoLabel.setScore(score)
        }

        cannotMoveDownNum = 0
        rotationsMade = 0
        currentTetromino = randomTetromino()

        if (!isNewTetrominoPositionable(currentTetromino, gameBoard)) {
            isRunning = false
            infoLabel.setGameOver(score)
        }
    }

    fun onTimerTick() {
        if (isRunning) {
            if (isTetrominoMovableDown(currentTetromino, gameBoard)) currentTetromino.point.y++
            else cannotMoveDownNum++

            if (cannotMoveDownNum >= numberOfTicksBeforeNewTetromino.value) cannotMoveDown()
        }
    }

    private fun findDeletableTableRows(): List<Int> {
        val deletableRows = mutableListOf<Int>()

        for (j in 0 until gameMatrix.height) {
            var counter = 0
            for (i in 0 until gameMatrix.width) {
                if (gameBoard[i][j] != null) counter++
            }
            if (counter == gameMatrix.width) deletableRows.add(j)
        }

        return deletableRows
    }

    private fun deleteTableRows(deletableTableRows: List<Int>) {
        deletableTableRows.forEach {
            var counter = gameMatrix.height - 1
            for (j in (0 until gameMatrix.height).reversed()) {
                if (j == it) continue
                for (i in 0 until gameMatrix.width) {
                    gameBoard[i][counter] = gameBoard[i][j]
                }
                counter--
            }

            for (i in 0 until gameMatrix.width) {
                gameBoard[i][0] = null
            }
        }
    }
}