BUILDROOT= tmp
TARGET=mediaindonesia
SRCDIR=../..

release: clean
	mkdir -p $(BUILDROOT)
	cp -rf $(SRCDIR)/*.xml $(BUILDROOT)
	cp -rf $(SRCDIR)/*.properties $(BUILDROOT)
	cp -rf $(SRCDIR)/*.cfg $(BUILDROOT)
	cp -rf $(SRCDIR)/keystore $(BUILDROOT)/
	cp -rf $(SRCDIR)/src/ $(BUILDROOT)
	cp -rf $(SRCDIR)/libs/ $(BUILDROOT)
	cp -rf res/ $(BUILDROOT)
	replace id.co.mondial.android.rss.mtsreader id.co.mondial.android.rss.$(TARGET) -- $(BUILDROOT)/src/id/co/mondial/android/rss/mtsreader/*.java $(BUILDROOT)/AndroidManifest.xml 
	mv  $(BUILDROOT)/src/id/co/mondial/android/rss/mtsreader/ $(BUILDROOT)/src/id/co/mondial/android/rss/$(TARGET)
	cd $(BUILDROOT) && ant release
	cp $(BUILDROOT)/bin/RssFeeds-release.apk ../releases/$(TARGET).apk
	
clean:
	rm -rf $(BUILDROOT)
	
