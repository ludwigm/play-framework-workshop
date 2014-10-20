import models.{EntryRecord, EntryDAO, DB}
import org.specs2.runner._
import org.junit.runner._

import org.specs2.mutable._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Ultra simple webapp")
    }

    "get all entries with json endpoint" in new WithApplication {
      val entriesNr = EntryDAO.findAll.length

      val endpoint = route(FakeRequest(GET, "/api/entries")).get

      status(endpoint) must equalTo(OK)

      import models.EntryRecord.entryReads

      Json.parse(contentAsString(endpoint)).as[Seq[EntryRecord]].length must equalTo (entriesNr)

    }
  }
}
