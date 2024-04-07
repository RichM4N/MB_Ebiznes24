package controllers

import javax.inject._
import play.api._
import play.api.mvc._


class Product(var name: String, var price: Double)
{

}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  var ProductList  = scala.collection.mutable.Map.empty[Int, Product]
  ProductList += (1 -> new Product("IPhone", 2000));
  ProductList += (2 -> new Product("Samsung", 1200.5));
  ProductList += (3 -> new Product("Nokia", 1500));
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def showAll = Action{ implicit request: Request[AnyContent] =>
    var productsString = "";
    ProductList.foreach{case (k, prod) =>
      productsString = productsString + "ID:" + k +  " Name: " + prod.name + " Price: " + prod.price + "<br>";
    } 
    Ok(productsString).as(HTML)
  }

  def getById = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def add = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def updateByID = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def deleteByID = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
