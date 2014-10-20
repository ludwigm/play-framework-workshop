package models
import sorm._

import utils.Implicits._

/**
 * Created by ludwigm on 01.09.14.
 */
object EntryDAO {

  def addEntry(entry:EntryRecord) = {
    DB.save(entry)
  }

  def findAll = {
    DB.query[EntryRecord].fetch().toList.sortBy(_.created)
  }
}
