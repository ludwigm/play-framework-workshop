package models
import sorm._

object DB extends Instance(
  entities = Set(Entity[EntryRecord]()),
  url = "jdbc:h2:file:data/db"
)