import org.koin.core.context.startKoin
import utils.modules.allModules

fun main() {
    startKoin {
        modules(allModules)
    }
}