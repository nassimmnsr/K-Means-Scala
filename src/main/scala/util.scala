import io.AnsiColor._

object util:
  def display(message: String): Unit =
    println()
    println(YELLOW + BOLD + "*" * 45 + RESET)
    println(YELLOW + BOLD + message + RESET)
    println(YELLOW + BOLD + "*" * 45 + RESET)
    println()

  def displayTitle(message: String): Unit =
    println()
    println(GREEN + BOLD + "*" * 45 + RESET)
    println(GREEN + BOLD + message + RESET)
    println(GREEN + BOLD + "*" * 45 + RESET)
    println()