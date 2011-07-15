BRAND_DIR = new-brands
RELEASES_DIR = $(BRAND_DIR)/releases

all: mediaindonesia

clean:
	rm -rf $(RELEASES_DIR)/*
	cd $(BRAND_DIR)/mediaindonesia && make clean
	
mediaindonesia: clean
	cd $(BRAND_DIR)/mediaindonesia && make
