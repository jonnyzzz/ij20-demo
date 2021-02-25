import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.measureTimeMillis

fun main() {
    val ijCommunityHome = Path.of("/Users/jonnyzzz/Work/intellij-community")

    repeat(5) {
        val time = measureTimeMillis {
            Files.walk(ijCommunityHome)
                .parallel()
                .filter { Files.isRegularFile(it) }
                .forEach { runCatching { Files.readAllBytes(it) } }
        }

        println("The read is completed in: ${time}ms")
    }
}
