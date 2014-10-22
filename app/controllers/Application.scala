package controllers


import models.{EntryRecord, EntryDAO}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._
import play.api.mvc.Controller

object Application extends Controller {

  def index = Action { implicit request =>
    val entries = EntryDAO.findAll
    var form : Form[EntryRecord]= null
    if(!request.flash.data.isEmpty){
      form = EntryRecord.entryForm.bind(request.flash.data)
    } else {
      form = EntryRecord.entryForm
    }

    Ok(views.html.index(form, entries))
  }

  def newEntry() = Action { implicit request =>
    val formData = EntryRecord.entryForm.bindFromRequest()
    formData.fold(
      hasErrors = { formData =>
        println(formData)
        Redirect(routes.Application.index).flashing(Flash(formData.data) + ("error"->"Validation failed"))
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



}


