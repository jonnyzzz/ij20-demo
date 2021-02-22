import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import kotlin.system.measureTimeMillis

fun main() {
    val home = Path.of("/Users/jonnyzzz/Work/intellij-community")

    repeat(5) {
        val time = measureTimeMillis {
            Files.walk(home).parallel()
                .filter { Files.isRegularFile(it) }
                .filter {
                    val name = it.fileName.toString()
                    name.endsWith(".java") ||
                            name.endsWith(".kt")
                }.map {
                    it to Files.readString(it, StandardCharsets.UTF_8).contains("Swapper32")
                }
                .collect(Collectors.toList())
        }

        println("Java + Kotlin read is completed in: ${time}ms")
    }
}
