MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "https://www.torproject.org"
SUMMARY = "Communicate anonymously over the internet"
DESCRIPTION = "Tor is a network of virtual tunnels that allows people and groups to improve their privacy and security on the Internet. It also enables software developers to create new communication tools with built-in privacy features. Tor provides the foundation for a range of applications that allow organizations and individuals to share information over public networks without compromising their privacy."
PROVIDES = "tor"
DEPENDS_${PN} += " \
	libevent \
	libminiupnpc \
"
RDEPENDS_${PN} += " \
	libevent \
"
PR = "r0"

SRCREV = "1cda452bc136de6bad4f203cea8f195599aec242"
SRC_URI = " \
	git://gitweb.torproject.org/tor.git \
"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42880bac4780915cfdf2e8157b74b71b"
S = "${WORKDIR}/git"

FILES_${PN} = " \
	${bindir}/tor* \
	${datadir}/tor/geoip \
	${datadir}/tor/geoip6 \
"
FILES_${PN}-doc = " \
	${sysconfdir}/tor/torrc.sample \
	${datadir}/doc/tor/* \
"

inherit autotools

