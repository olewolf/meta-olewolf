MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://elinks.or.cz"
SUMMARY = "Text based browser for the command line"
DESCRIPTION = "ELinks is an advanced and well-established feature-rich text mode web (HTTP/FTP/..) browser. ELinks can render both frames and tables, is highly customizable and can be extended via Lua or Guile scripts."
PROVIDES = "elinks"
DEPENDS += " \
	virtual/gettext \
"
PR = "r5"

SRC_URI = " \
	git://elinks.or.cz/elinks.git;protocol=http \
"
SRCREV = "7b6a76da4b697174c2ede1abf8f93f031cdf44db"
S = "${WORKDIR}/git/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=759ed239c8364afc037c8ab486b55f1f"

inherit autotools

do_configure_prepend () {
	./autogen.sh
}

do_configure () {
	CFLAGS="${BUILD_CFLAGS}" ./configure --host=${TARGET_ARCH} --prefix=${D}${prefix}
	exit 0
}

fakeroot do_install () {
	oe_runmake install MKINSTALLDIRS="mkdir -p ${D}/$1"
	exit 0
}
