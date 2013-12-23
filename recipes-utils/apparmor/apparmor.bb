MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://apparmor.net"
SUMMARY = "User-space tool for AppArmor"
DESCRIPTION = "Provides initialization scripts required for the AppArmor Mandatory Access Control system, including the AppArmor Parser, which is required to convert AppArmor text profiles to a kernel binary format."
PROVIDES = "apparmor"

SRC_URI = " \
	git://git.kernel.org/pub/scm/linux/kernel/git/jj/linux-apparmor \
"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c39c6201d8bd97b9ac82d536fdc22bd3"
S = "${WORKDIR}/git/"



inherit autotools

