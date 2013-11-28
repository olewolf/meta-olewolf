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
REQUIRES_${PN} += " libdb++ libboost-system libboost-filesystem libboost-program-optoins libboost-thread libboost-chrono libboost-system-unit-test-framework libprotoc "
RREQUIRES_${PN} += " libdb++ libboost-system libboost-filesystem libboost-program-optoins libboost-thread libboost-chrono libprotoc "
PROVIDES = "bitcoind"

#FILES_${PN} = " \
#"

inherit autotools


EXTRA_OECONF += " --with-incompatible-bdb --with-boost-libdir=${STAGING_LIBDIR}"
#EXTRA_OECONF += " --with-boost-libdir=${STAGING_LIBDIR}"

#HACKED_LIBCB_CXX_H="${STAGING_INCDIR}/libdb_cxx.hpp"

#do_configure_prepend () {
#	# Hack db_cxx to point to libdb_cxx.
#	if [ ! -h ${HACKED_LIBCB_CXX_H} ]; then
#		ln -s db_cxx.h ${HACKED_LIBCB_CXX_H}
#	fi
#}

#do_configure () {
#	CFLAGS="$TARGET_CFLAGS -system${STAGING_INCDIR}" ./configure --prefix=${D}${prefix} --host=${TARGET_ARCH}
#}

#do_make () {
#
#}

#do_configure_append () {
#	# Unhack libdb_cxx link.
#	if [ -h ${HACKED_LIBCB_CXX_H} ]; then
#	   rm -f ${HACKED_LIBCB_CXX_H}
#	fi
#}
