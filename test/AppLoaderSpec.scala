import play.api.test._

class AppLoaderSpec extends PlaySpecification {

  val loader = new AppLoader

  "AppLoader" should {

    "handle requests to the hello route" in new WithApplicationLoader(loader) {
      val name = "beachape"
      val request = FakeRequest(GET, s"/hello/$name")

      route(request) must beSome.which { result =>
        status(result) mustEqual OK
        contentAsString(result) mustEqual s"Hello $name"
      }
    }

    "handle requests to the sqrt route" in new WithApplicationLoader(loader) {
      val request = FakeRequest(GET, "/sqrt/1764")

      route(request) must beSome.which { result =>
        status(result) mustEqual OK
        contentAsString(result) mustEqual "42.0"
      }
    }
  }
}
