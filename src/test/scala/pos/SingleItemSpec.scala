package pos

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FreeSpec, Matchers}


class SingleItemSpec extends FreeSpec with Matchers with MockFactory {

  val priceDisplay: StubDisplay = new StubDisplay()
  val priceLookup: PriceLookup  = stub[PriceLookup]
  val pos: PointOfSale          = new PointOfSale(priceDisplay, priceLookup)

  "The POS system" - {
    "displays the price of a single item, given a barcode" in {
      val aBarcode = "abcde\n"
      priceLookup.priceForBarcode _ when aBarcode returns 1.00f

      pos.onBarcode(aBarcode)

      priceDisplay.receivedPrice shouldBe "$1.0" // TODO: update price format to always have 2 decimal digits.
    }

    "displays an error message if the barcode received is empty" in {
      pos.onBarcode("")

      priceDisplay.receivedPrice shouldBe "Barcode read was empty"
    }

    // need to introduce some form of mocks. Will complete after previous tests pass with mock
    "displays an error message if the price looked up is not found" ignore {
      pos.onBarcode("non existing product")

      priceDisplay.receivedPrice shouldBe "No item was found for the the barcode requested"
    }
  }
}

class StubDisplay() extends Display {
  var messageToDisplay: String = _

  def render(messageToDisplay: String): Unit = {
    this.messageToDisplay = messageToDisplay
  }

  def receivedPrice: String = messageToDisplay
}
