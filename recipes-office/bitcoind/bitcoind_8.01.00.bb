MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = ""
SUMMARY = ""
DESCRIPTION = ""
PROVIDES = "bitcoind"

SRC_URI = " \
	git://github.com/bitcoin/bitcoin.git \
"
SRCREV = "${AUTOREV}"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=c39c6201d8bd97b9ac82d536fdc22bd3"
S = "${WORKDIR}/git/"

RRECOMMENDS_${PN} += " ntp "
REQUIRES_${PN} += " db libboost-system libboost-filesystem libboost-program-optoins libboost-thread libboost-chrono libboost-system-unit-test-framework libprotoc "
RREQUIRES_${PN} += " db libboost-system libboost-filesystem libboost-program-optoins libboost-thread libboost-chrono libprotoc "
PROVIDES = "bitcoind"

#FILES_${PN} = " \
#"

inherit autotools


EXTRA_OECONF += " --with-incompatible-bdb --with-boost-libdir=${STAGING_LIBDIR}"
