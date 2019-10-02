package com.undefinedlabs.scope.akka.pingpongspec

import akka.actor.{Actor, ActorRef}
import com.undefinedlabs.scope.akka.pingpongspec.messages.{Peer, Ping, Pong, Start}

class PingActor extends Actor {

  private var peer : ActorRef = _

  override def receive: Receive = {
    case peerMsg : Peer => peer = peerMsg.peer
    case startMsg : Start =>
      Thread.sleep(100)
      this.peer.tell(Ping(startMsg.rebounds), self)
    case pongMsg : Pong =>
      Thread.sleep(200)
      if (pongMsg.rebounds != 0) this.peer.tell(Ping(pongMsg.rebounds - 1), self)
    case _ => unhandled()
  }
}
