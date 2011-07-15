BRAND_DIR = new-brands
RELEASES_DIR = $(BRAND_DIR)/releases

all: mediaindonesia kompas

clean:
	rm -rf $(RELEASES_DIR)/*.apk
	cd $(BRAND_DIR)/mediaindonesia && make clean

mediaindonesia: clean
	cd $(BRAND_DIR)/kompas && make

kompas: clean
	cd $(BRAND_DIR)/kompas && make
