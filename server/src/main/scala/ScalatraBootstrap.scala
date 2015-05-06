import controller._
import jp.sf.amateras.scalatra.forms.ValidationJavaScriptProvider
import org.scalatra._
import javax.servlet._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new IndexController, "/")
    context.mount(new ValidationJavaScriptProvider, "/assets/js/*")
  }
}