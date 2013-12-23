MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://bitcoin.org"
SUMMARY = "peer-to-peer network based digital currency"
DESCRIPTION = "Bitcoin is a free open source peer-to-peer electronic cash system that is completely decentralized, without the need for a central server or trusted parties.  Users hold the crypto keys to their own money and transact directly with each other, with the help of a P2P network to check for double-spending."
PROVIDES = "bitcoind"

SRC_URI = " \
	git://github.com/bitcoin/bitcoin.git \
"
SRCREV = "${AUTOREV}"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=c39c6201d8bd97b9ac82d536fdc22bd3"
S = "${WORKDIR}/git/"

RRECOMMENDS_${PN} += " ntp "
DEPENDS += " libdb++ libboost-system libboost-filesystem libboost-program-options libboost-thread libboost-chrono "
RDEPENDS_${PN} += " libdb++ libboost-system libboost-filesystem libboost-program-options libboost-thread libboost-chrono "
PROVIDES = "bitcoind"

FILES_${PN}-dbg += " ${bindir}/test_bitcoin "
FILES_${PN} = " ${bindir}/bitcoin-cli ${bindir}/bitcoind "

inherit autotools

EXTRA_OECONF += " --with-incompatible-bdb --with-boost-libdir=${STAGING_LIBDIR}"
EXTRA_OEMAKE += "${PARALLEL_MAKE}"

do_compile () {
	# Remove "-pipe" from the options because the pipe gets too big.
	CFLAGS=$(echo "${CFLAGS}" | sed -n -e 's/-\<pipe\>//p')
	oe_runmake
}
