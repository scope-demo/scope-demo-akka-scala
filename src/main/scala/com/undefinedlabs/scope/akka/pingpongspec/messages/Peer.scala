package com.undefinedlabs.scope.akka.pingpongspec.messages

import akka.actor.ActorRef

final case class Peer(peer:ActorRef) extends Serializable {

}
