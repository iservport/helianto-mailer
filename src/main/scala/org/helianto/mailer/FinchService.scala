package org.helianto.mailer

import akka.actor.{ActorRef, ActorSystem}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.iservport.message.domain.{ContactData, Message, MessageData, MessageDefaults}
import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import io.finch._
import io.finch.jackson._

import scala.concurrent.ExecutionContextExecutor

/**
  * Created by mauriciofernandesdecastro on 19/02/16.
  */
trait FinchService {

  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  val senderActor: ActorRef

  val health: Endpoint[String] = get(/) {
    Ok("Healthy")
  }

  val send: Endpoint[String] = post("send" :: body.as[Message]) { m: Message =>
    senderActor ! m
    Ok("Sent")
  }

  val test: Endpoint[String] = get("test") {
    senderActor ! SampleMessage.content
    Ok("Test")
  }

  val api: Service[Request, Response] = (health :+: send :+: test).toService

}
