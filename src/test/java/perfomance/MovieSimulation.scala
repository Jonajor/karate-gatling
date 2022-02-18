package perfomance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class MovieSimulation extends Simulation {

  val protocol = karateProtocol(
    "/movies" -> Nil
  )

  /*protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")*/

  val create = scenario("Teste para criar novo filme").exec(karateFeature("classpath:movie/simple/create-movie.feature"))
//  val update = scenario("Teste para alterar filme").exec(karateFeature("classpath:movie/simple/update-movie.feature"))
  setUp(
    create.inject(rampUsers(5) during (5 seconds)).protocols(protocol),
//    update.inject(rampUsers(5) during (5 seconds)).protocols(protocol)
  )
}