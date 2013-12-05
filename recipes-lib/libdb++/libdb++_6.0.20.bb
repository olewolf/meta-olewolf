MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.oracle.com/technetwork/products/berkeleydb/overview"
SUMMARY = "Oracle Berkeley DB"
DESCRIPTION = "Berkeley DB enables the development of custom data management solutions, without the overhead traditionally associated with such custom projects. Berkeley DB provides a collection of well-proven building-block technologies that can be configured to address any application need from the hand-held device to the datacenter, from a local storage solution to a world-wide distributed one, from kilobytes to petabytes."

VIRTUAL_NAME ?= "virtual/db"
RCONFLICTS_${PN} += " db3 "

PROVIDES += " \
	${VIRTUAL_NAME} \
	libdb++ \
"

PR = "r0"

SRC_URI = " \
	http://download.oracle.com/berkeley-db/db-6.0.20.tar.gz \
"
SRC_URI[md5sum] = "f73afcb308aefde7e6ece4caa87b22a9"
SRC_URI[sha256sum] = "806a8a443854e339f07a62d5e010baf48203bce0bbb49e32f245184c9f522203"

LICENSE = "AGPv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ec8b0b17cc31513fe35ab10716f8490"

S = "${WORKDIR}/db-6.0.20/"

FILES_${PN}-doc += " \
	${docdir}/* \
"

FILES_${PN} += " \
	${libdir}/*.so* \
	${libdir}/*.la*} \
	${bindir}/db_* \
"

EXTRA_OECONF = " --enable-compat185 --enable-dbm --disable-static --enable-cxx --enable-smallbuild "
EXTRA_OEMAKE = " -I. "

do_configure () {
	cd ${S}build_unix
	../dist/configure --prefix=${D}${prefix} --host=${TARGET_ARCH} ${EXTRA_OECONF}
}

do_compile () {
	cd ${S}build_unix
	oe_runmake
}

fakeroot do_install () {
	cd ${S}build_unix
	oe_runmake docdir=${D}${docdir} install
}
