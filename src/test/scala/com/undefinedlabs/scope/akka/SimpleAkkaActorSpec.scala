package com.undefinedlabs.scope.akka

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.undefinedlabs.scope.akka.simplespec.PrinterActor
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}



class SimpleAkkaActorSpec
  extends TestKit(ActorSystem("SimpleAkkaActorSpec"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll : Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "An Actor Pipeline" must {

    "send messages each other" in {
      val lastActorProps = PrinterActor.props(ActorRef.noSender)
      val lastActor = system.actorOf(lastActorProps, "Actor_C")

      val middleActorProps = PrinterActor.props(lastActor)
      val middleActor = system.actorOf(middleActorProps, "Actor_B")

      val initialActorProps = PrinterActor.props(middleActor)
      val initialActor = system.actorOf(initialActorProps, "Actor_A")

      initialActor.tell("Hello World", ActorRef.noSender)
      initialActor.tell("Another Hello World", ActorRef.noSender)
      Thread.sleep(3000)
    }

  }

}
