MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.rtai.org"
SUMMARY = "Real Time Application Interface"
DESCRIPTION = "RTAI is a real-time application interfaces intended to ease real-time development by providing an array of services."
PROVIDES = "rtai"
DEPENDS += "virtual/kernel"

SRC_URI = " \
	https://www.rtai.org/RTAI/rtai-3.9.2.tar.bz2 \
"
SRC_URI[md5sum] = "3e622c4d66bbce593e80ed751fae4208"
SRC_URI[sha256sum] = "249de1bf4054eb61f2e72dd690fdae0c09887160d928dd25b921c7cad3b27dde"
LICENSE = "GPLv2"
#LIC_FILES_CHKSUM = "file://COPYING;md5="
S = "${WORKDIR}/${PN}-${PV}/"

inherit autotools gettext

EXTRA_OECONF += " \
	--disable-dox-doc \
	--with-linux-dir=${STAGING_KERNEL_DIR} \
"

do_configure () {
	oe_runconf ${EXTRA_OECONF} 
}
