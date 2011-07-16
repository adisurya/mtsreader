BRAND_DIR = new-brands
RELEASES_DIR = $(BRAND_DIR)/releases

all: mediaindonesia kompas vivanews tnol

clean:
	rm -rf $(RELEASES_DIR)/*.apk
	cd $(BRAND_DIR)/mediaindonesia && make clean
	cd $(BRAND_DIR)/kompas && make clean
	cd $(BRAND_DIR)/vivanews && make clean
	cd $(BRAND_DIR)/tnol && make clean

mediaindonesia:
	cd $(BRAND_DIR)/kompas && make

kompas:
	cd $(BRAND_DIR)/kompas && make

vivanews:
	cd $(BRAND_DIR)/vivanews && make

tnol:
	cd $(BRAND_DIR)/tnol && make
	