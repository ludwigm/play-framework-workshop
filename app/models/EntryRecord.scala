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

  implicit val entryWrites = Json.writes[EntryRecord]

  implicit val entryReads = Json.reads[EntryRecord]

  val entryForm : Form[EntryRecord] = Form(
    mapping(
      "message"->text(3,20)
    )
      (apply   = EntryRecord.applyPartial)
      (unapply = EntryRecord.unapplyPartial))

}


