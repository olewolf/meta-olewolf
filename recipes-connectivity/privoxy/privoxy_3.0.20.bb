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

PACKAGES = "${PN}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"
S = "${WORKDIR}/current/"

FILES_${PN} = " \
	${sbindir}/privoxy \
	${sysconfdir}/privoxy/* \
	${sysconfdir}/privoxy/templates/* \
	${sysconfdir}/init.d/* \
	${runstatedir}/run/* \
"
FILES_${PN}-doc = " \
	${docdir}/privoxy/* \
	${man1dir}/privoxy.1 \
"

inherit autotools


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
	# Manual install because the make script bails out if the user isn't
	# the one that is expected.
	install -m 0750 -D ${S}privoxy ${D}${sbindir}/privoxy
	install -m 0750 -d -D ${D}${sysconfdir}/privoxy/templates
	for f in ${S}templates/*; do
		install -m 0644 $f ${D}${sysconfdir}/privoxy/templates/
	done
	for f in ${S}{user.action,match-all.action,config,trust,default.action.master,default.filter,default.action,user.filter}; do
		install -m 0640 $f ${D}${sysconfdir}/privoxy
	done
	install -m 0750 -d -D ${D}${localstatedir}/run/privoxy
	install -m 0755 -D ${S}debian/init.d ${D}${sysconfdir}/init.d/privoxy
}


pkg_preinst_${PN} () {
	#!/bin/sh

	useradd -r -M -U -s -c "Privoxy http proxy" privoxy
	exit 0
}

pkg_postinst_${PN} () {
	#!/bin/sh

	chown -R privoxy:privoxy ${sysconfdir}/privoxy
	chown -R privoxy:privoxy ${sysconfdir}
	mkdir -p ${localstatedir}/run/privoxy
	chown -R privoxy:privoxy ${localstatedir}/run/privoxy
	chmod 750 ${localstatedir}/run/privoxy
	exit 0
}

pkg_prerm_${PN} () {
	#!/bin/sh

	${sysconfdir}/init.d/privoxy stop
	exit 0
}

pkg_postrm_${PN} () {
	#!/bin/sh

	userdel privoxy
	groupdel privoxy
	rm -rf ${localstatedir}/run/privoxy
	exit 0
}

