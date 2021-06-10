# language: es



@FeatureName:CheckoutOrdenCompra-Komet
Característica: Verificar proceso de compra de  un producto con el 20 % descuento

  @ScenarioName:CheckoutOrdenCompraProductoDescuento
  Esquema del escenario:Checkout Orden Compra Producto Descuento
    Dado que un usuario ingresa a la plataforma de compras
    Cuando selecciona un prenda con el descuento de <VALORDESCUENTO> %, de talla <TALLA>, de color <COLOR>
    Entonces se verifica que se haya cargado la prenda en el carrito
    Y se verifica la información de la orden de compra
    Y se confirma la compra

    Ejemplos:
      | VALORDESCUENTO | TALLA | COLOR |
      | 20             | L     | Green |