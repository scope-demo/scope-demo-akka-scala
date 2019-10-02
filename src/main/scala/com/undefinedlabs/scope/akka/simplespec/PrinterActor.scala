package com.undefinedlabs.scope.akka.simplespec

import java.util.Random

import akka.actor.{Actor, ActorRef, Props}
import okhttp3.{OkHttpClient, Request}
import org.slf4j.{Logger, LoggerFactory}

object PrinterActor {
  val LOGGER : Logger = LoggerFactory.getLogger(PrinterActor.getClass)

  def props(nextActor : ActorRef) = {
    Props(new PrinterActor(nextActor))
  }
}

class PrinterActor(next:ActorRef) extends Actor {

  override def receive: Receive = {
    case message : String => {
      System.out.println(message)
      Thread.sleep(new Random(System.currentTimeMillis).nextInt(500))

      val okHttpClient = new OkHttpClient.Builder().build
      val reqBuilder = new Request.Builder().url("http://www.google.com")
      PrinterActor.LOGGER.info("Executing HttpClient request to http://www.google.com")
      val execute = okHttpClient.newCall(reqBuilder.build).execute

      Thread.sleep(new Random(System.currentTimeMillis).nextInt(500))
      if (next != null) next.tell(message, self)
    }
    case _ => unhandled()
  }
}
