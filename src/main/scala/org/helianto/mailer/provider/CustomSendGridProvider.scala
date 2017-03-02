package org.helianto.mailer.provider

import java.net.URLEncoder
import javax.mail.internet.MimeUtility

import com.coiney.akka.mailer.MailerSystem.Settings
import com.coiney.akka.mailer.protocol.Email
import com.coiney.akka.mailer.providers.MailerProvider.Mailer
import com.coiney.akka.mailer.providers.SendgridProvider
import com.sendgrid.smtpapi.SMTPAPI
import org.slf4j.LoggerFactory

/**
  * Created by Eldevan Nery Junior on 07/03/16.
  *
  * used code from "com.coiney.akka.mailer"
  */
class CustomSendGridProvider(settings: Settings) extends SendgridProvider(settings) {

  override def getMailer: Mailer = new CustomSendgridMailer(settings)

  class CustomSendgridMailer(settings: Settings) extends SendgridMailer(settings) {

    override def postDataMap(email: Email): Map[String, String] = {
      // add Headers
      // To Be Done
      var postData = super.postDataMap(email)
      postData += ("x-smtpapi" -> new CustomSendGridProviderUtil().mapToHeader(email.headers))
      postData
    }

    override def urlEncode(postData: Map[String, String]): String =
      postData.foldRight(""){ case ((key, value), acc) => s"$key=${URLEncoder.encode(Option(value).getOrElse(""), "UTF-8")}&$acc"}

  }


}

class CustomSendGridProviderUtil{

  val logger = LoggerFactory.getLogger(classOf[CustomSendGridProviderUtil])

  def  mapToHeader(map: Map[String, String]): String = {
    val smtpApi = new SMTPAPI()
    map.foreach {
      case (key, value) => {
        if (key.equals("template_id")) {
          smtpApi.addFilter("templates", "template_id", value);
        } else if (value!=null) {
          smtpApi.addSubstitution(key, {
            new String(MimeUtility.encodeText(value))
          })
        } else {
          logger.info("Invalid key {}", key)
        }
        println(s" $key -> $value")
      }
    }
    smtpApi.addFilter("templates", "enabled", 1);
    smtpApi.jsonString();
  }
}
