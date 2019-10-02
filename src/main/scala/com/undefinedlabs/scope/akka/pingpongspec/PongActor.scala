package com.undefinedlabs.scope.akka.pingpongspec

import akka.actor.{Actor, ActorRef}
import com.undefinedlabs.scope.akka.pingpongspec.messages.{Peer, Ping, Pong}

class PongActor extends Actor {

  private var peer : ActorRef = _;

  override def receive: Receive = {
    case peerMsg : Peer => peer = peerMsg.peer
    case pingMsg : Ping =>
      Thread.sleep(200)
      if (pingMsg.rebounds != 0) this.peer.tell(Pong(pingMsg.rebounds), self)
  }
}
