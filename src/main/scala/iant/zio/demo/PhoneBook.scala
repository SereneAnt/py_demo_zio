package iant.zio.demo

import zio._
import zio.console._

object PhoneBook extends App {

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] =
    program.fold(_ => 1, _ => 0)

  val program: ZIO[Console, Throwable, Unit] = for {
    db <- Ref.make(Map.empty[String, String]) // initialize DB
    _  <- cmdLoop(db)
          .catchAll { err =>
            putStrLn(s"Action failed: ${err.getMessage}".colored(scala.Console.RED))
          }.repeat(Schedule.forever)
  } yield()

  implicit class Colorizer(val s: String) extends AnyVal {
    def colored(color: String = scala.Console.BLUE) =
      s"$color$s${scala.Console.RESET}"
  }

  type PhoneBook = Ref[Map[String, String]]

  private def cmdLoop(db: PhoneBook) = for {
    _   <- putStrLn("""Enter action:
                      |    c(reate) name number
                      |    r(ead) [name]
                      |    u(pdate) name number
                      |    d(delete) name
                    """.stripMargin)
    cmd <- getStrLn
    _   <- runCmd(db, cmd)
  } yield ()

  private def runCmd(db: PhoneBook, cmd: String) = {

    def create(name: String, number: String) = db.modify //Not idempotent!!!
      { records =>
        records.get(name) match {
          case Some(_) => (true, records)
          case None    => (false, records + ((name, number)))
        }
      }.flatMap { exists =>
        if (exists) ZIO.fail(
          new RuntimeException(s"Record for '$name' already exists!")
        ) else putStrLn(s"Record created - $name: $number".colored())
      }
    def read(name: String) = db.get.flatMap { records =>
        val msg = records.get(name).fold(s"Record for '$name' does not exist!")(
                  number => s"Record retrieved - $name: $number")
        putStrLn(msg.colored())
      }
    def readAll = db.get.flatMap { records =>
      putStrLn(records.mkString(", ").colored())
    }
    def update(name: String, number: String) =
      db.update{ _ + ((name, number)) } *>
      putStrLn(s"Record created - $name: $number".colored())
    def delete(name: String) =
      db.update(_ - name) *>
      putStrLn(s"Record deleted - $name".colored())

    Task
      .effect(cmd.split("\\s+").toList)
      .collectM(new RuntimeException("Invalid command: " + cmd)) {
        case c :: name :: num :: Nil if "create".startsWith(c) => create(name, num)
        case r :: name        :: Nil if "read".startsWith(r)   => read(name)
        case r                :: Nil if "read".startsWith(r)   => readAll
        case u :: name :: num :: Nil if "update".startsWith(u) => update(name, num)
        case d :: name        :: Nil if "delete".startsWith(d) => delete(name)
      }
    }
}
