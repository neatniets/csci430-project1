.PHONY: all clean
OBJS := $(patsubst %.java, %.class, $(wildcard *.java))
JC := javac

all: $(OBJS)

clean:
	-rm *.class

UserInterface.class: UserInterface.java Warehouse.class Product.class \
	Supplier.class Client.class
	$(JC) $<

Warehouse.class: Warehouse.java ProductList.class Product.class Client.class \
	Supplier.class ClientList.class SupplierList.class shoppingCart.class
	$(JC) $<

ProductList.class: ProductList.java Product.class
	$(JC) $<

Product.class: Product.java WaitlistEntry.class ProductIdServer.class
	$(JC) $<

ProductIdServer.class: ProductIdServer.java
	$(JC) $<

WaitlistEntry.class: WaitlistEntry.java
	$(JC) $<

ClientList.class: ClientList.java Client.class
	$(JC) $<

Client.class: Client.java shoppingCart.class ClientIdServer.class
	$(JC) $<

ClientIdServer.class: ClientIdServer.java
	$(JC) $<

shoppingCart.class: shoppingCart.java
	$(JC) $<

SupplierList.class: SupplierList.java Supplier.class
	$(JC) $<

Supplier.class: Supplier.java
	$(JC) $<

Transaction.class: Transaction.java
	$(JC) $<
