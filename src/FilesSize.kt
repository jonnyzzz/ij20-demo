import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val home = Path.of("/Users/jonnyzzz/Work/intellij-community")
    val r = Files.walk(home).parallel()
        .filter { Files.isRegularFile(it) }
        .filter {
        val name = it.fileName.toString()
        name.endsWith(".java") ||
        name.endsWith(".kt")
    }.map { Files.size(it) }.collect(Collectors.summingLong { it })

    println("Java + Kotlin size: $r")
}
