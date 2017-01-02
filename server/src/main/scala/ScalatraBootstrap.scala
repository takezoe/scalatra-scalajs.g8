import controller._
import org.scalatra._
import javax.servlet._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new IndexController, "/")
  }
}
