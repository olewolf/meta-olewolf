MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.privoxy.org/"
SUMMARY = "HTTP proxy which increases your privacy"
DESCRIPTION = "Privoxy is a non-caching web proxy with advanced filtering capabilities for enhancing privacy, modifying web page data and HTTP headers, controlling access, and removing ads and other obnoxious Internet junk.  Privoxy has a flexible configuration and can be customized to suit individual needs and tastes.  It has application for both stand-alone systems and multi-user networks."
PROVIDES = "privoxy"
DEPENDS += " \
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

inherit autotools

PACKAGES = "${PN} ${PN}-doc ${PN}-dbg"

FILES_${PN} = " \
	${sbindir}/privoxy \
	${sysconfdir}/privoxy/* \
	${sysconfdir}/privoxy/templates/* \
	${sysconfdir}/init.d/* \
	${localstatedir}/run/* \
"
FILES_${PN}-doc = " \
	${docdir}/privoxy/* \
	${man1dir}/privoxy.1 \
"
FILES_${PN}-dbg += " \
	${prefix}/src/debug/privoxy/${PR}-${PV}/current/* \
	${sbindir}/.debug/* \
"

EXTRA_OECONF += " \
	--enable-zlib \
	--prefix=/usr \
	--sysconfdir=/etc/privoxy \
	--localstatedir=/var \
"

do_configure_prepend () {
	autoheader
	autoconf
}

do_compile () {
	pmake="mkdir -p" oe_runmake
}

do_install () {
	# Manual install because the make script bails out if the user isn't
	# the one that is expected.
	install -m 0755 -d -D ${D}${sbindir}
	install -m 0755 ${S}privoxy ${D}${sbindir}/privoxy

	install -m 0755 -d -D ${D}${sysconfdir}
	install -m 0755 -d -D ${D}${sysconfdir}/privoxy
	for f in ${S}{user.action,match-all.action,config,trust,default.action.master,default.filter,default.action,user.filter}; do
		install -m 0640 ${f} ${D}${sysconfdir}/privoxy/
	done
	install -m 0755 -d -D ${D}${sysconfdir}/privoxy/templates
	for f in ${S}templates/*; do
		install -m 0644 ${f} ${D}${sysconfdir}/privoxy/templates/
	done

	install -m 0755 -d -D ${D}${localstatedir}
	install -m 0755 -d -D ${D}${localstatedir}/run
	install -m 0755 -d -D ${D}${localstatedir}/run/privoxy

	install -m 0755 -d ${D}${sysconfdir}/init.d
	install -m 0755 -D ${S}debian/init.d ${D}${sysconfdir}/init.d/privoxy
}


pkg_preinst_${PN}_append () {
#!/bin/sh

${sbindir}/useradd -r -M -U -c "Privoxy http proxy" -s ${sbindir}/nologin privoxy
exit 0
}

pkg_postinst_${PN}_append () {
#!/bin/sh

chown -R privoxy:privoxy ${sysconfdir}/privoxy
chown -R privoxy:privoxy ${sysconfdir}
mkdir -p ${localstatedir}/run/privoxy
chown -R privoxy:privoxy ${localstatedir}/run/privoxy
chmod 750 ${localstatedir}/run/privoxy
exit 0
}

pkg_prerm_${PN}_append () {
#!/bin/sh

${sysconfdir}/init.d/privoxy stop
exit 0
}

pkg_postrm_${PN}_append () {
#!/bin/sh

${sbindir}/userdel privoxy
${sbindir}/groupdel privoxy
rm -rf ${localstatedir}/run/privoxy
exit 0
}

