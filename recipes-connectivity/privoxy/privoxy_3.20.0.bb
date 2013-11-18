MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.privoxy.org/"
SUMMARY = "HTTP proxy which increases your privacy"
DESCRIPTION = "Privoxy is a non-caching web proxy with advanced filtering capabilities for enhancing privacy, modifying web page data and HTTP headers, controlling access, and removing ads and other obnoxious Internet junk.  Privoxy has a flexible configuration and can be customized to suit individual needs and tastes.  It has application for both stand-alone systems and multi-user networks."
PROVIDES = "privoxy"
DEPENDS_${PN} += " \
	libpcre \
"
RDEPENDS_${PN} += " \
	libpcre \
"
PR = "r0"

SRC_URI = " \
	cvs://anonymous@ijbswa.cvs.sourceforge.net/cvsroot/ijbswa;module=current;method=pserver \
"

PACKAGES += "${PN} ${PN}-doc"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"
S = "${WORKDIR}/current/"

FILES_${PN} = " \
	${sbindir}/privoxy \
	${sysconfdir}/privoxy/* \
	${runstatedir}/privoxy/* \
	${localstatedir}/log/privoxy/logfile \
"
FILES_${PN}-doc = " \
	${docdir}/privoxy/* \
	${man1dir}/privoxy.1 \
"

EXTRA_OECONF += " \
	--enable-zlib \
	--sysconfdir=/etc/privoxy \
	--localstatedir=/var \
"

do_configure_prepend () {
	autoheader
	autoconf
}

do_compile () {
	oe_runmake
}

do_install () {
	DESTDIR=${D} make install
}

inherit autotools

