package ds.design

/**
  * A Interface to allow the client interact with the Iterator.
  * 
    @see [[ds.design.Iterator]]
  * @author thaleslim
  */

trait Aggregate[ConcreteIterator]{
    /** Interface to initialize a Iterator 
      *
        @see [[ds.design.Iterator]]
      */
    def createIterator(): ConcreteIterator
}
