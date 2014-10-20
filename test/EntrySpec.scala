import models.EntryRecord
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._
import sorm._
import models.DB

@RunWith(classOf[JUnitRunner])
class EntrySpec extends Specification {

  "Entry" should {

    "be creatable" in new WithApplication{
      val entry = DB.save(EntryRecord("foo"))
      entry.id must not (beNull)
      entry.message must beEqualTo ("foo")
    }

  }
}
