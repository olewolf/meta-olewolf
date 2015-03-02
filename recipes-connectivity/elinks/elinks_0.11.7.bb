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
	http://elinks.cz/elinks.git;protocol=http \
"
#SRC_URI = " \
#	git://repo.or.cz/elinks.git;protocol=git \
#"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;beginline=102;md5=f4a282f76f374d84d493f7c9b2a0e98d"
SRC_URI[sha256sum] = "1f47e26ed17ed11e51f0d482b64793dbeacb5b5e3c2eab5234f125984e970b14"

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
