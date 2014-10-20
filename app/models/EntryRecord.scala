package models


import org.joda.time.DateTime
import play.api.data.Form
import play.api.data.Forms._
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

  val entryForm : Form[EntryRecord] = Form(
    mapping(
      "message"->text(3,20)
    )
      (apply   = EntryRecord.applyPartial)
      (unapply = EntryRecord.unapplyPartial))

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


