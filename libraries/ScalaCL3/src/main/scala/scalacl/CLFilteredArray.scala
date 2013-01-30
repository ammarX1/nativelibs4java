package scalacl

import scalacl.impl.ScheduledBuffer
import scalacl.impl.DataIO
import scalacl.impl.ScheduledBufferComposite
import scalacl.impl.DefaultScheduledData

case class CLFilteredArray[T](array: CLArray[T], presenceMask: CLArray[Boolean])(implicit io: DataIO[T], context: Context)
  extends ScheduledBufferComposite {

  private[scalacl] override def foreachBuffer(f: ScheduledBuffer[_] => Unit) {
    array.foreachBuffer(f)
    presenceMask.foreachBuffer(f)
  }

  def map[U](f: T => U): CLFilteredArray[U] = sys.error("not implemented")
  def compact: CLArray[T] = sys.error("not implemented")

  def toCLArray = compact
    
  def toArray: Array[T] =
    toCLArray.toArray
    
  def toList: List[T] =
    toArray.toList
  
  def toSeq: Seq[T] = 
    toArray.toSeq
  
  override def toString = "(" + array + ", " + presenceMask + ")"
}