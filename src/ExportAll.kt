import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val home = Path.of("/Users/jonnyzzz/Work/intellij-icons")
    val allFiles = Files.walk(home)
        .filter { Files.isRegularFile(it) }
        .filter {
            val ext = it.fileName.toString().toLowerCase()
            ext.endsWith(".png") ||
                    ext.endsWith(".svg") ||
                    ext.endsWith(".gif")
        }
        .collect(Collectors.toList())
        .sortedBy { it.fileName.toString().toLowerCase() }

    val trash = Path.of("/Users/jonnyzzz/Work/intellij-icons-trash")
    Files.createDirectories(trash)

    val html = buildString {
        appendLine("<html>")
        appendLine("<body>")

        appendLine("<table border=0>")
        allFiles.chunked(10).forEach { chunk ->
            appendLine("<tr>")
            for (file in chunk) {
                appendLine("<td>")
                appendLine("<a href='$file'>")
                appendLine("<img src='${file.fileName}' width=150 alt='$file'/>")
                appendLine("</a>")
                appendLine("</td>")
            }
            appendLine("</tr>")
        }
        appendLine("</table>")

        appendLine("</body>")
        appendLine("</html>")
    }

    Files.writeString(trash.resolve("___index.html"), html)
}
