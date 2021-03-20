.PHONY: all clean
OBJS := $(patsubst %.java, %.class, $(wildcard *.java))
JC := javac

all: $(OBJS)

clean:
	-rm *.class

WarehouseContext.class: %.class: %.java WarehouseState.java LoginState.java
	$(JC) $<

WarehouseState.class: %.class: %.java
	$(JC) $<

LoginState.class: %.class: %.java WarehouseState.java ClientMenuState.java \
	ClerkMenuState.java ManagerMenuState.java Warehouse.java
	$(JC) $<

ClientMenuState.class: %.class: %.java WarehouseState.java
	$(JC) $<

ClerkMenuState.class: %.class: %.java WarehouseState.java
	$(JC) $<

ManagerMenuState.class: %.class: %.java WarehouseState.java
	$(JC) $<

Warehouse.class: Warehouse.java ProductList.java Product.java Client.java \
	Supplier.java ClientList.java SupplierList.java shoppingCart.java \
	Transaction.java WaitlistEntry.java Supply.java Money.java
	$(JC) $<

ProductList.class: ProductList.java Product.java
	$(JC) $<

Product.class: Product.java WaitlistEntry.java ProductIdServer.java \
	Supply.java Money.java
	$(JC) $<

ProductIdServer.class: ProductIdServer.java
	$(JC) $<

WaitlistEntry.class: WaitlistEntry.java
	$(JC) $<

ClientList.class: ClientList.java Client.java
	$(JC) $<

Client.class: Client.java shoppingCart.java ClientIdServer.java \
	Transaction.java CartItem.java Money.java
	$(JC) $<

ClientIdServer.class: ClientIdServer.java
	$(JC) $<

shoppingCart.class: shoppingCart.java CartItem.java Product.java
	$(JC) $<

SupplierList.class: SupplierList.java Supplier.java
	$(JC) $<

Supplier.class: Supplier.java SupplierIdServer.java Supply.java
	$(JC) $<

SupplierIdServer.class: %.class: %.java
	$(JC) $<

Transaction.class: Transaction.java TransactionIdServer.java Money.java
	$(JC) $<

TransactionIdServer.class: %.class: %.java
	$(JC) $<

CartItem.class: CartItem.java Product.java
	$(JC) $<

Supply.class: Supply.java
	$(JC) $<

Money.class: Money.java
	$(JC) $<

UserInterface.class: %.class: %.java
	$(JC) $<
