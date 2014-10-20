package controllers

import java.util.Date

import models.{EntryRecord, EntryDAO}
import org.joda.time.DateTime
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._


object Application extends Controller {

  def index = Action {
    val entries = EntryDAO.findAll
    println(entries)
    Ok(views.html.index(entryForm, entries))
  }

  def newEntry() = Action { implicit request =>
    val formData = entryForm.bindFromRequest()
    formData.fold(
      hasErrors = { formData =>
        println(formData)
        BadRequest("Error: " + formData.errors.map( e => e.key + " -> " + e.message).mkString(", "))
      },
      success =   { entry =>
        println(entry)
        EntryDAO.addEntry(entry)
        Redirect(routes.Application.index)
      })
  }

  def listEntries = Action {
    import models.EntryRecord.entryWrites

    val entries = EntryDAO.findAll
    val jsonEntries = Json.toJson(entries)
    Ok(jsonEntries)
  }



  private val entryForm : Form[EntryRecord] = Form(
    mapping(
      "message"->text(3,20)
    )
      (apply   = EntryRecord.applyPartial)
      (unapply = EntryRecord.unapplyPartial))
}


