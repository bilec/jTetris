package gui.label

import javax.swing.JLabel

class InfoLabel : JLabel() {
    init {
        text = "Game not started"
    }

    fun setScore(score: Int) {
        text = "Score: $score"
    }

    fun setGameOver(score: Int) {
        text = "Game over, score: $score"
    }

    fun setPaused() {
        text = "Paused"
    }
}