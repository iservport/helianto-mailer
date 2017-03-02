package org.helianto.mailer.actor

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.event.LoggingReceive
import com.coiney.akka.mailer.protocol.{Correspondent, Email}
import com.iservport.message.domain.{ContactData, Message, MessageData, MessageDefaults}

/**
  * Message actor.
  */
class SenderActor(mailerService:ActorRef) extends Actor with ActorLogging {

  import SenderActor._

  override def receive: Receive = LoggingReceive {
    case message: Message => mailerService ! email(message)
    case _ => sender ! "Sending"
  }

}
object SenderActor {

  def email(message: Message): Email = new Email(message.getMessageData.getSubject
    , toCorrespondent(message.getSender)
    , to = List(toCorrespondent(message.getRecipient))
    , html = Some("<hr/>")
    , headers = headers(message))

  def toCorrespondent(contact: ContactData) = Correspondent(contact.getContactEmail, Some(contact.getContactName))

  def headers(message: Message): Map[String, String] = Map(
      "template_id"           -> message.getTemplate
    , "${senderEmail}"        -> message.getSender.getContactEmail
    , "${recipientEmail}"     -> message.getRecipient.getContactEmail
    , "${recipientFirstName}" -> message.getRecipient.getContactName
    , "${contextLogo}"        -> message.getContextLogo
    , "${entityName}"         -> message.getRecipient.getEntityName
    , "${callBackUri}"        -> s"${message.getApiHome}${message.getServicePath}/${message.getToken}"
    , "${unsubscribeUri}"     -> s"${message.getApiHome}/unsubscribe${message.getServicePath}/${message.getToken}"
  ) ++ headers(message.getMessageData) ++ headers(message.getDefaults)

  private def headers(defaults: MessageDefaults): Map[String, String] = Map(
    "${greeting}"           -> defaults.getGreeting,
    "${seeOnline}"          -> defaults.getSeeOnline,
    "${fallBack}"           -> defaults.getFallBack,
    "${sentByText}"         -> defaults.getSentByText,
    "${disclaimer}"         -> defaults.getDisclaimer,
    "${unsubscribeText}"    -> defaults.getUnsubscribeText,
    "${unsubscribeCaption}" -> defaults.getUnsubscribeCaption,
    "${unsubscribeService}" -> defaults.getUnsubscribeService,
    "${ensure}"             -> defaults.getEnsure,
    "${copyright}"          -> defaults.getCopyright
  )

  private def headers(data: MessageData): Map[String, String] = Map(
    "${subject}"           -> data.getSubject,
    "${title}"             -> data.getTitle,
    "${procedure}"         -> data.getProcedure,
    "${callToAction}"      -> data.getCallToAction,
    "${trailingInfo}"      -> data.getTrailingInfo
  )

}