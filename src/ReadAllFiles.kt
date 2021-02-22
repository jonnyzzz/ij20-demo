import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import kotlin.system.measureTimeMillis

fun main() {
    val home = Path.of("/Users/jonnyzzz/Work/intellij-community")

    repeat(5) {
        val time = measureTimeMillis {

            Files.walk(home)
                .parallel()
                .filter { Files.isRegularFile(it) }
                .forEach { runCatching { Files.readAllBytes(it) } }
        }

            println("Java + Kotlin read is completed in: ${time}ms")
        }
    }

    private fun String.endsWithAny(vararg suffix: String) = suffix.any { endsWith(it) }
