BRAND_DIR = new-brands
RELEASES_DIR = $(BRAND_DIR)/releases

all: antaranews mediaindonesia tnol vivanews 

clean:
	rm -rf $(RELEASES_DIR)/*.apk
	cd $(BRAND_DIR)/antaranews && make clean
	cd $(BRAND_DIR)/mediaindonesia && make clean
	cd $(BRAND_DIR)/tnol && make clean
	cd $(BRAND_DIR)/vivanews && make clean
	
mediaindonesia:
	cd $(BRAND_DIR)/mediaindonesia && make

vivanews:
	cd $(BRAND_DIR)/vivanews && make

tnol:
	cd $(BRAND_DIR)/tnol && make

antaranews:
	cd $(BRAND_DIR)/antaranews && make
