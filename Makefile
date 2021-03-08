.PHONY: all clean
OBJS := $(patsubst %.java, %.class, $(wildcard *.java))
JC := javac

all: $(OBJS)

clean:
	-rm $(OBJS)

UserInterface.class: UserInterface.java Warehouse.class Product.class \
	Supplier.class Client.class Transaction.class WaitlistEntry.class \
	Supply.class Money.class
	$(JC) $<

Warehouse.class: Warehouse.java ProductList.class Product.class Client.class \
	Supplier.class ClientList.class SupplierList.class shoppingCart.class \
	Transaction.class WaitlistEntry.class Supply.class Money.class
	$(JC) $<

ProductList.class: ProductList.java Product.class
	$(JC) $<

Product.class: Product.java WaitlistEntry.class ProductIdServer.class \
	Supply.class Money.class
	$(JC) $<

ProductIdServer.class: ProductIdServer.java
	$(JC) $<

WaitlistEntry.class: WaitlistEntry.java
	$(JC) $<

ClientList.class: ClientList.java Client.class
	$(JC) $<

Client.class: Client.java shoppingCart.class ClientIdServer.class \
	Transaction.class CartItem.class Money.class
	$(JC) $<

ClientIdServer.class: ClientIdServer.java
	$(JC) $<

shoppingCart.class: shoppingCart.java CartItem.class Product.class
	$(JC) $<

SupplierList.class: SupplierList.java Supplier.class
	$(JC) $<

Supplier.class: Supplier.java SupplierIdServer.class Supply.class
	$(JC) $<

SupplierIdServer.class: %.class: %.java
	$(JC) $<

Transaction.class: Transaction.java TransactionIdServer.class Money.class
	$(JC) $<

TransactionIdServer.class: %.class: %.java
	$(JC) $<

CartItem.class: CartItem.java Product.class
	$(JC) $<

Supply.class: Supply.java
	$(JC) $<

Money.class: Money.java
	$(JC) $<
