package ds.mutable

import ds.design.Aggregate
import ds.concreteIterator.LinkedListIterable

/**
  * The Linked List' abstract element, a representation
  * that allows it's linear behaviour.
  *
    @see [[LinkedList]]
  * @author rbonifacio / thaleslim 
  */

case class NodeList[T](val value: T, var next: NodeList[T])

/**
  * A trait List' implementation using a group of nodes.
  * 
    This' nodes are capable of pointing to the next index,
  * which together represent a sequence. The trait List' 
  * linearity is given by this behaviour instead of the 
  * node' physical placement.
  * 
  * @author rbonifacio / thaleslim
  */

class LinkedList[T <% Comparable[T]] extends List[T] with Aggregate[LinkedListIterable[T]]{

  private var head: NodeList[T] = null

  def insert(idx: Int, value: T){
    if(idx >= 0 && idx <= this.size) {
        if(idx == 0)
            head = NodeList(value, head) 
        else {
            val node = nodeAtPosition(idx-1)
            node.next = NodeList(value, node.next)
        }
    }
    else throw ds.mutable.InvalidArgument("InvalidArgs: Out of Range")
  }

  def remove(idx: Int): Unit = {
    if(idx < 0 || idx >= this.size)
      throw ds.mutable.InvalidArgument("InvalidArgs: Out of Range")
    
    if(idx == 0)
      head = head.next
    else {
      val node = nodeAtPosition(idx-1)
      node.next = node.next.next
    }
  }

  def elementAt(idx: Int): Option[T] = {
    if(idx < 0 || idx > this.size)
        return None

    val node = nodeAtPosition(idx)

    if( node != null )
        return Some(node.value)
    else
        return None
  }

  def find(value: T): Option[Int] = {
    if( this.size == 0 ) return None
    var it = head
    var idx = 0
    while( it != null ) {
        if( value.compareTo(it.value) == 0 )
            return Some(idx)
        it = it.next
        idx += 1
    }
    return None
  }

  def addAll[B<:T](that: List[B]) : List[T] = {
    for(index <- 0 until that.size){
        that.elementAt(index) match{
            case Some(value) => this.insert(this.size,value)
            case _ => throw new InvalidArgument("ooops..... unexpected")
        }
    }
    return this
  }

  def clear(): Unit =  while( this.size > 0 ) { this.remove( this.size - 1 ) }

  private def nodeAtPosition(idx: Int): NodeList[T] = {
    if(idx < 0 || idx > this.size) null
    var it = head
    for(index <- 0 until idx) {
        it = it.next
    }
    return it
  }
  
  def createIterator(): LinkedListIterable[T] = return new LinkedListIterable[T](this.head)

  def foreach[U](fun: T => U): Unit = {
    var cursor = this.createIterator

    cursor.first

    while( !cursor ){
        fun(cursor.currentItem)
        cursor.next
    }

  }

  def map(fun: T => T): LinkedList[T] = {
    var cursor = this.createIterator
    var that = new LinkedList[T]

    cursor.first
    
    while( !cursor ){
        that.insert( that.size, fun(cursor.currentItem) )
        cursor.next
    }

    this.subst(that)

    return that

  }

  def reduce[A](fun: (A,T) => A)(start: A): A = {
    var cursor  = this.createIterator
    var result: A = start

    cursor.first
    
    while( !cursor ){
        result = fun( result, cursor.currentItem )
        cursor.next
    }

    return result
  }

  def filter(fun: T => Boolean): LinkedList[T] = {
    var cursor = this.createIterator
    var that = new LinkedList[T]

    cursor.first
    
    while( !cursor ){
        
        if( fun( cursor.currentItem ) )
            that.insert( that.size, cursor.currentItem )
        cursor.next
    }

    this.subst(that)

    return that
  }

}
