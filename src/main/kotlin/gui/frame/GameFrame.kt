package gui.frame

import gui.label.InfoLabel
import gui.menubar.GameMenuBar
import gui.panel.GamePanel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.config.Config
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class GameFrame: JFrame(), KoinComponent {
    private val config: Config by inject()
    private val infoLabel: InfoLabel by inject()
    private val gamePanel: GamePanel by inject()
    private val gameMenuBar: GameMenuBar by inject()

    init {
        title = config.appName
        defaultCloseOperation = EXIT_ON_CLOSE

        size = Dimension(config.gameWindowSize.width, config.gameWindowSize.height)
        isResizable = false

        jMenuBar = gameMenuBar
        add(gamePanel)
        add(infoLabel, BorderLayout.SOUTH)

        isVisible = true
    }
}