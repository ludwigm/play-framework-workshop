package models
import sorm._

import utils.Implicits._

/**
 * Created by ludwigm on 01.09.14.
 */
object EntryDAO {

  //private var entries = List[EntryRecord]()

  def addEntry(entry:EntryRecord) = {
    //entries = entry :: entries
    DB.save(entry)
  }

  def findAll = {
    //entries.sortBy(_.created)
    DB.query[EntryRecord].fetch().toList
  }
}
