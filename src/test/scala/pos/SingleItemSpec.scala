package pos

import org.scalatest.{FreeSpec, Matchers}


class SingleItemSpec extends FreeSpec with Matchers {

  val priceDisplay: StubDisplay = new StubDisplay()
  val pos: PointOfSale = new PointOfSale(priceDisplay)

  "The POS system" - {
    "displays the price of a single item, given a barcode" in {
      pos.onBarcode("abcde\n")

      priceDisplay.receivedPrice shouldBe "$1"
    }

    "displays an error message if the barcode received is empty" in {
      pos.onBarcode("")

      priceDisplay.receivedPrice shouldBe "Barcode read was empty"
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
