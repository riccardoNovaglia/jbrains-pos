package pos

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FreeSpec, Matchers}


class SingleItemSpec extends FreeSpec with Matchers with MockFactory {

  val priceDisplay: StubDisplay = new StubDisplay()
  val priceLookup: PriceLookup  = stub[PriceLookup]
  val pos: PointOfSale          = new PointOfSale(priceDisplay, priceLookup)

  val aBarcode = "barcode\n"

  "The POS system" - {
    "displays the price of a single item, given a barcode" in {
      priceLookup.priceForBarcode _ when aBarcode returns 1.00f

      pos.onBarcode(aBarcode)

      priceDisplay.receivedPrice shouldBe "$1.00"
    }

    "displays an error message if the barcode received is empty" in {
      pos.onBarcode("")

      priceDisplay.receivedPrice shouldBe "Barcode read was empty"
    }

    "displays an error message if the price looked up is not found" in {
      priceLookup.priceForBarcode _ when aBarcode throws new PriceNotFoundException(aBarcode)

      pos.onBarcode(aBarcode)

      priceDisplay.receivedPrice shouldBe "No item was found for the barcode requested"
    }

    "formats the price of items to always have 2 decimal digits" in {
      priceLookup.priceForBarcode _ when aBarcode returns 1.1f

      pos.onBarcode(aBarcode)

      priceDisplay.receivedPrice shouldBe "$1.10"
    }

    "displays an error if price lookup fails with exception" in {
      priceLookup.priceForBarcode _ when aBarcode throws new RuntimeException("ouch")

      pos.onBarcode(aBarcode)

      priceDisplay.receivedPrice shouldBe "An error occurred looking up the price for the barcode requested"
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
