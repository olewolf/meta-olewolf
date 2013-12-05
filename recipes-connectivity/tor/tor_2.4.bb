MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "https://www.torproject.org"
SUMMARY = "Communicate anonymously over the internet"
DESCRIPTION = "Tor is a network of virtual tunnels that allows people and groups to improve their privacy and security on the Internet. It also enables software developers to create new communication tools with built-in privacy features. Tor provides the foundation for a range of applications that allow organizations and individuals to share information over public networks without compromising their privacy."
PROVIDES = "tor"
DEPENDS += " \
	libevent \
"
RDEPENDS_${PN} += " \
	libevent \
"
PR = "r0"

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://gitweb.torproject.org/tor.git \
"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev ${PN}-doc"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d5601db6c1fc95a0f938a69101c505aa"
S = "${WORKDIR}/git"

FILES_${PN} = " \
	${bindir}/tor* \
	${datadir}/tor/geoip \
	${datadir}/tor/geoip6 \
"
FILES_${PN}-doc += " \
	${sysconfdir}/tor/torrc.sample \
"

inherit autotools

