MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://elinks.or.cz"
SUMMARY = "Text based browser for the command line"
DESCRIPTION = "ELinks is an advanced and well-established feature-rich text mode web (HTTP/FTP/..) browser. ELinks can render both frames and tables, is highly customizable and can be extended via Lua or Guile scripts."
PROVIDES = "elinks"
DEPENDS += " \
	virtual/gettext \
"
PR = "r6"

SRC_URI = " \
	git://elinks.or.cz/elinks.git;protocol=http \
"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a0056b7c5071a89f43a8ad44158448d"

inherit autotools

do_configure_prepend () {
	./autogen.sh
}

do_configure () {
	ARCHITECTURE="$(echo ${CPP} | sed -n -e 's/^\([^-]*\).*/\1/p')"
	oe_runconf CFLAGS="${BUILD_CPPFLAGS}" --host=${ARCHITECTURE} --prefix=${D}${prefix}
}

do_install () {
	make install DESTDIR="${D}" MKINSTALLDIRS="mkdir -p"
	exit 0
}
