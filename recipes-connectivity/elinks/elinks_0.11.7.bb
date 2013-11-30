MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://elinks.or.cz"
SUMMARY = "Text based browser for the command line"
DESCRIPTION = "ELinks is an advanced and well-established feature-rich text mode web (HTTP/FTP/..) browser. ELinks can render both frames and tables, is highly customizable and can be extended via Lua or Guile scripts."
PROVIDES = "elinks"
DEPENDS_${PN} += " \
	virtual/gettext \
"
RDEPENDS_${PN} += " \
"
PR = "r0"

#Git server refuses connections
#SRC_URI = " \
#	git://elinks.cz/elinks.git \
#"
#SRCREV = "7b6a76da4b697174c2ede1abf8f93f031cdf44db"
SRC_URI = " \
	http://elinks.or.cz/download/elinks-current-stable.tar.bz2 \
"
SRC_URI[md5sum] = "f77425a717ddb66eac4507aa0ada14d5"
SRC_URI[sha256sum] = "2be60597f04e5b185eaaf6d88cf557a2c6b35d86b20b7823a7860e0100d26d1f"


LICENSE = ""
LIC_FILES_CHKSUM = "file://LICENSE;"
S = "${WORKDIR}/${PN}-${PV}/"

do_configure_${PN}_prepend () {
	# Rename the unpacked directory from today's date to the package name.
	cd ${WORKDIR}
	rm -r ${PN}-${PV}
	# find doesn't seem to support repetitions, and complains that the
	# directory doesn't exist once it has been renamed, so hack around
	# those WTFs.
	find . -type d -regex '^\./elinks-.*[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$' -exec mv {} ${PN}-${PV} \; || true
	exit 1
}

inherit autotools

