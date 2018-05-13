package ds.design

/**
  * A Iterador is a object that allows a client to traverse through container (a collection of elements).
  *
    This is usefull specially with linear or hierarchy based containers; also the name Iterator
  * could refer the design as a whole including the Aggregate interface the concrete iterators.
  * @see [[Aggregate]] [[concreteIterator]]
  * 
  * @author thaleslim
  */

trait Iterator[T]{
    /** Gets the value inside this */
    def currentItem(): T          
    /** Gets this current position */
    def currentIndex(): Int
    /** Sets this to the Container' beginning */
    def first(): Unit
    /** Sets this to the next element in the container */
    def next(): Unit
    /** Checks if has already reached the end or if the container is empty */
    def isDone(): Boolean
    /** Alternative to this.isDone */
    def apply(): Boolean = this.isDone()
    /** Alternative to !(this.isDone) */
    def unary_!(): Boolean = !this.isDone()
}
