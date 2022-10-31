package gui.frame

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.data.IsHelpVisible
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JLabel

class HelpFrame : JFrame(), KoinComponent {
    private val gameFrame: GameFrame by inject()
    private val isHelpVisible: IsHelpVisible by inject()

    init {
        title = "Help"
        size = Dimension(160, 120)
        location = gameFrame.location
        isResizable = false
        defaultCloseOperation = DISPOSE_ON_CLOSE
        layout = BoxLayout(contentPane, BoxLayout.Y_AXIS)

        val up = JLabel("↑ - rotate")
        val down = JLabel("↓ - move down faster")
        val left = JLabel("← - move left")
        val right = JLabel("→ - move right")
        val p = JLabel("p - pause game")

        add(up)
        add(down)
        add(left)
        add(right)
        add(p)

        isVisible = true
    }

    override fun dispose() {
        super.dispose()
        isHelpVisible.value = false
    }
}