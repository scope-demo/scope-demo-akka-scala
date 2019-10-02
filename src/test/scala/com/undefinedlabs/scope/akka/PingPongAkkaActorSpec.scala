package com.undefinedlabs.scope.akka

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.undefinedlabs.scope.akka.pingpongspec.messages.{Peer, Start}
import com.undefinedlabs.scope.akka.pingpongspec.{PingActor, PongActor}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class PingPongAkkaActorSpec
    extends TestKit(ActorSystem("PingPongAkkaActorSpec"))
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll : Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A Ping actor and Pong actor" must {
    "should send messages each other" in {
      val pingActor = system.actorOf(Props(new PingActor), "PingActor")
      val pongActor = system.actorOf(Props(new PongActor), "PongActor")

      pingActor.tell(Peer(pongActor), ActorRef.noSender)
      pongActor.tell(Peer(pingActor), ActorRef.noSender)

      pingActor.tell(Start(5), ActorRef.noSender)

      Thread.sleep(5000)
    }
  }

}
