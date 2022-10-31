package game

import game.tetrominoes.Tetromino
import java.awt.Color

fun isTetrominoMovableToRight(tetromino: Tetromino, gameBoard: Array<Array<Color?>>): Boolean {
    val currentRotation = tetromino.currentRotation()

    currentRotation.indices.forEach { i ->
        val lastOne = currentRotation[i].indexOfLast { it == 1 } + tetromino.point.x
        if ((lastOne + 1) >= gameBoard.size) return false
        if (gameBoard[lastOne + 1][i] != null) return false
    }

    for (i in currentRotation.indices) {
        for (j in currentRotation[i].indices) {
            if (currentRotation[i][j] != 1) continue
            if ((tetromino.point.x + j + 1 < gameBoard.size) && (gameBoard[tetromino.point.x + j + 1][tetromino.point.y + i] != null)) return false
        }
    }

    return true
}

fun isTetrominoMovableToLeft(tetromino: Tetromino, gameBoard: Array<Array<Color?>>): Boolean {
    val currentRotation = tetromino.currentRotation()

    currentRotation.indices.forEach { i ->
        val firstOne = currentRotation[i].indexOfFirst { it == 1 } + tetromino.point.x
        if ((firstOne - 1) < 0) return false
        if (gameBoard[firstOne - 1][i] != null) return false
    }

    for (i in currentRotation.indices) {
        for (j in currentRotation[i].indices) {
            if (currentRotation[i][j] != 1) continue
            if ((tetromino.point.x + j - 1 >= 0) && (gameBoard[tetromino.point.x + j - 1][tetromino.point.y + i] != null)) return false
        }
    }

    return true
}

fun isTetrominoMovableDown(tetromino: Tetromino, gameBoard: Array<Array<Color?>>): Boolean {
    val currentRotation = tetromino.currentRotation()

    if ((tetromino.point.y + currentRotation.size + 1) > gameBoard.first().size) return false

    for (i in currentRotation.indices) {
        for (j in currentRotation[i].indices) {
            if (currentRotation[i][j] != 1) continue
            if (gameBoard[tetromino.point.x + j][tetromino.point.y + i + 1] != null) return false
        }
    }

    return true
}

fun isTetrominoRotatable(tetromino: Tetromino, gameBoard: Array<Array<Color?>>): Boolean {
    val possibleNextRotation = tetromino.possibleNextRotation()

    if (tetromino.point.x + possibleNextRotation.maxOf { it.size } > gameBoard.size) return false
    if (tetromino.point.y + possibleNextRotation.size > gameBoard.first().size) return false

    for (i in possibleNextRotation.indices) {
        for (j in possibleNextRotation[i].indices) {
            if (possibleNextRotation[i][j] != 1) continue
            if (gameBoard[tetromino.point.x + j][tetromino.point.y + i] != null) return false
        }
    }

    return true
}

fun isNewTetrominoPositionable(tetromino: Tetromino, gameBoard: Array<Array<Color?>>): Boolean {
    val currentRotation = tetromino.currentRotation()

    for (i in currentRotation.indices) {
        for (j in currentRotation[i].indices) {
            if (currentRotation[i][j] != 1) continue
            if (gameBoard[tetromino.point.x + j][tetromino.point.y + i] != null) return false
        }
    }

    return true
}