package ds.mutable

import ds.design.Aggregate
import ds.concreteIterator.ArrayListIterable

/**
  * A trait List' implementation using sequential
  * allocation (Array).
  *
  * @author rbonifacio / thaleslim
  */

class CustomArray[T <% Comparable[T]: Manifest](private val max: Int = 10) extends List[T]{

  private var _size = 0
  private var elements = Array.ofDim[T](max)
  private var default_value = elements(0)

  def insert(idx: Int, value: T): Unit = {
    if(idx >= 0 && idx < max) {
      this.elements(idx) = value
      this._size += 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def remove(idx: Int): Unit = {
    var size = this.length
    if(idx >= 0 && idx < size) {
      this.elements(idx) = this.default_value
      this._size -= 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def elementAt(idx: Int): Option[T] = {
    if(_size > 0 && idx >= 0 && idx < max && elements(idx) != default_value)
        return Some(elements(idx))
    else
        return None
  }

  def find(value: T): Option[Int] = {
    for(idx <- 0 until this.max) {
        if( value.compareTo(elements(idx)) == 0 )
            return Some(idx)
    }
    return None
  }
/*
  def addAll[B <: T](that: CustomArray[B]): List[T] = {
    var newCustomArray = new CustomArray[T](this.size + that.size + 1)

    for(i <- 0 until this.length) this.elementAt(i) match {
        case Some(value) => newCustomArray.insert(i,value)
        case _ => {}
    }

    for(idx <- 0 until that.length) that.elementAt(idx) match {
        case Some(value) => newCustomArray.elementAt(idx) match {
            case None => newCustomArray.insert(idx, value)
            case Some(x) => {
                var iidx = idx + 1
                var done = false
                while( iidx < newCustomArray.size && !done ) {
                    if( newCustomArray.elementAt(idx) != None )
                        iidx += 1
                    else
                        done = true
                }
                newCustomArray.elementAt(iidx) match {
                    case None => newCustomArray.insert(iidx,value)
                    case _ => throw new InvalidArgument("ooops..... unexpected")
                }
            }
        }
        case _ => {}
    }

    var newElements = Array.ofDim[T](newCustomArray.length)

    for(j <- 0 until newCustomArray.length) newCustomArray.elementAt(j) match {
        case Some(value) => { newElements(j) = value }
        case _ => {}
    }

    this.elements = newElements

    this._size = newCustomArray.size

    return newCustomArray
  }
*/
  //TODO: Melhorar addAll, knows too much
  def addAll[B <: T](that: List[B]): List[T] = {
    var newCustomArray = new CustomArray[T](this.size + that.size + 1)

    for(i <- 0 until this.length) this.elementAt(i) match {
        case Some(value) => newCustomArray.insert(i,value)
        case _ => {}
    }

    for(idx <- 0 until that.size) that.elementAt(idx) match {
        case Some(value) => newCustomArray.elementAt(idx) match {
            case None => newCustomArray.insert(idx, value)
            case Some(x) => {
                var iidx = idx + 1
                var done = false
                while( iidx < newCustomArray.size && !done ) {
                    if( newCustomArray.elementAt(idx) != None )
                        iidx += 1
                    else
                        done = true
                }
                newCustomArray.elementAt(iidx) match {
                    case None => newCustomArray.insert(iidx,value)
                    case _ => throw new InvalidArgument("ooops..... unexpected")
                }
            }
        }
        case _ => {}
    }

    var newElements = Array.ofDim[T](newCustomArray.length)

    for(j <- 0 until newCustomArray.length) newCustomArray.elementAt(j) match {
        case Some(value) => { newElements(j) = value }
        case _ => {}
    }

    this.elements = newElements

    this._size = newCustomArray.size
    
    return newCustomArray
  }

  def clear(): Unit = while( this.size > 0 ) { this.remove( this.size - 1 ) }

  def length(): Int = this.max

  override def size(): Int = this._size

  def createIterator(): ArrayListIterable[T] = return new ArrayListIterable[T](this)

  def foreach[U](fun: T => U): Unit = {
    var cursor = this.createIterator

    cursor.first

    while( !cursor ){
        fun(cursor.currentItem)
        cursor.next
    }

  }

  def map(fun: T => T): ArrayList[T] = {
    var cursor = this.createIterator
    var that = new ArrayList[T](this.max)

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

  def filter(fun: T => Boolean): ArrayList[T] = {
    var cursor = this.createIterator
    var that = new ArrayList[T](this.max)

    cursor.first
    
    while( !cursor ){
        if( fun( cursor.currentItem ) )
            that.insert( that.size, cursor.currentItem )
        cursor.next
    }

    this.subst(that)

    return that
  }

//  def print() = println(this.reduce[String]{ _ + _ + " -> " }(""))
}
