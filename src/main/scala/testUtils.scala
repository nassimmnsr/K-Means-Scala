import io.AnsiColor._

object testUtils:
  def display(message: String): Unit =
    println()
    println(YELLOW + BOLD + "*" * 35 + RESET)
    println(YELLOW + BOLD + message + RESET)
    println(YELLOW + BOLD + "*" * 35 + RESET)
    println()

  def displayTitle(message: String): Unit =
    println()
    println(GREEN + BOLD + "*" * 35 + RESET)
    println(GREEN + BOLD + message + RESET)
    println(GREEN + BOLD + "*" * 35 + RESET)
    println()