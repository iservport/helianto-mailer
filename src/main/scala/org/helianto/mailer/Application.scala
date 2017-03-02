package org.helianto.mailer

import akka.actor.{ActorSystem, Props}
import com.twitter.finagle.Http
import org.helianto.mailer.actor.SenderActor

//import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.coiney.akka.mailer.MailerSystem

/**
  * Created by mauriciofernandesdecastro on 17/02/16.
  */
object Application extends App with FinchService {

  override implicit val system = ActorSystem("helianto-mailer")
  override implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val mailerSystem = MailerSystem()
  val mailerService = mailerSystem.createService("helianto-mailer")

  lazy val senderActor = system.actorOf(Props(new SenderActor(mailerService)), "sndAct")

  Http.serve(":8082", api)

  def shutdown = {
    println("Mailer shutting down")
    system.terminate()
  }

}
