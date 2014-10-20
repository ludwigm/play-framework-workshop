package models


import org.joda.time.DateTime
import play.api.libs.json.{JsValue, Json, Writes}
import sorm._

case class EntryRecord(message:String, created:DateTime = DateTime.now)

object EntryRecord{
  def applyPartial(message:String) = {
    EntryRecord(message)
  }

  def unapplyPartial(entry:EntryRecord) = {
    Some(entry.message)
  }

  /*def apply(message:String, created:DateTime = DateTime.now) = {
    new EntryRecord(message, created)
  }*/

  /*def unapply(entryRecord:EntryRecord) = {
    Some(entryRecord.message, entryRecord.created)
  }*/

  implicit val entryWrites = Json.writes[EntryRecord]

  implicit val entryReads = Json.reads[EntryRecord]

/*
  implicit val entryWrites = new Writes[EntryRecord]{
    def writes(entry: EntryRecord): JsValue = {
      Json.obj(
        "message" -> entry.message,
        "created" -> entry.created
      )
    }
  }
  */
/*
  implicit val barReads = Json.reads[EntryRecord]
  */
}


