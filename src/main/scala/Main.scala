import scala.io.Source

object Main extends App {
  if (args.size > 0) {
    val filename = args(0)
    Console.println("processing %s".format(filename))

    for (line <- Source.fromFile(filename).getLines()) {
      println(line)
      println(ProductRecord(InputDataDTO(line)))
    }
  } else {
    Console.println("thanks for the coconuts")
  }
}
