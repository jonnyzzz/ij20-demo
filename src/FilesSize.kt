import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val home = Path.of("/Users/jonnyzzz/Work/intellij-community")
    val d = Files.walk(home).parallel()
        .filter { Files.isRegularFile(it) }
        .filter {
        val name = it.fileName.toString()
        name.endsWith(".java") ||
        name.endsWith(".kt")
    }.map { it to Files.size(it) }.collect(Collectors.toList())


    val topN = d.sortedByDescending { it.second }.take(10)

    println("The biggest files:")
    topN.forEach { println(it) }

     val r = d.stream()
        .collect(Collectors.summingLong { it.second })

    println("Java + Kotlin size: ${r / 1024 / 1024}mb")
}
