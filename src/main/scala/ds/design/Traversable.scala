package ds.design

import ds.mutable.List

/**
  * Interface to define a traversable class.
  * 
    Needs the method  ``` def foreach[U](fun: Elem => U): Unit ``` in which 
  * Elem corresponds to the container elements' type; to return things like
  * the cointainer' size   
  * 
  * @author thaleslim
  */

trait Traversable[T]{
    /** Executes a function for every element in a container, discarding its return value */
    def foreach[U](fun: T => U): Unit
    /** Calculates the number of elements of a container */
    def size(): Int = {
        var size = 0
        this.foreach[Int]{ x: T => size += 1; size }
        return size
    }
    /** Generates a container that holds the result from applying a method to this' elements.
      * 
        In other words, applies fun to every position of a container:
        - case true => Appends to result container
        - case false => Nothing or, in a mutable environment, removes this' element
      * @return new container holding the results
      */
    def map(fun: T => T): List[T]
    /** Applies a function foreach value of the container to reduce it to a single value. */
    def reduce[A](fun: (A,T) => A)(start: A): A
    /** Generates a container that holds the result from applying a filter to this' elements.
      * 
        In other words, applies fun to every position of a container:
        - case true => Appends to result container
        - case false => Nothing or, in a mutable environment, removes this' element
      * @return new container holding the results
      */
    def filter(fun: T => Boolean): List[T]

}