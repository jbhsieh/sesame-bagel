import scala.io.Source

object Main extends App {
  if (args.size > 0) {
    val filename = args(0)
    Console.println("processing %s".format(filename))
    val bufferedSource = Source.fromFile(filename)
    val recordCollection = for (line <- bufferedSource.getLines()) yield ProductRecord(InputDataDTO(line))
    for (record <- recordCollection) Console.println(record)
    bufferedSource.close
  } else {
    Console.println("thanks for the coconuts")
  }
}
