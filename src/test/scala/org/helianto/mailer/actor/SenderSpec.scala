package org.helianto.mailer.actor

import com.iservport.message.domain.{ContactData, Message, MessageData, MessageDefaults}
import org.helianto.mailer.UnitSpec


class SenderSpec extends UnitSpec {

  "Headers" should "contain all fields from message" in {
    val message = new Message()
      .sender(new ContactData("a@sender", "Sender").entityName("SENDER"))
      .recipient(new ContactData("a@recipient", "Recipient").entityName("RECIPIENT"))
      .apiHome("APIHOME")
      .servicePath("SERVICE")
      .token("TOKEN")
      .defaults(
        new MessageDefaults()
          .greeting("HELLO")
          .seeOnline("SEE")
          .fallBack("FALLBACK")
          .sentByText("SENTBY")
          .disclaimer("DISCLAIMER")
          .unsubscribeText("UNSBS1")
          .unsubscribeCaption("UNSBS2")
          .unsubscribeService("UNSBS3")
          .ensure("ENSURE")
          .copyright("CPRGHT")
      )
      .messageData(
        new MessageData()
          .subject("SUBJECT")
          .title("TITLE")
          .procedure("PROCEDURE")
          .callToAction("ACTION")
          .trailingInfo("ETC")
    )
    val headers = SenderActor.headers(message)
    println(headers)
  }

}
