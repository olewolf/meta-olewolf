MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://openbox.org"
SUMMARY = "Graphical menu editor for openbox"
DESCRIPTION = "Obmenu is a graphical menu editor for the Openbox window manager.  Openbox uses XML to store its menu preferences, and editing these by hand can quickly become tedious.  This utility provides a convenient method of editing the menu in a graphical interface, while not losing the powerful features of Openbox such as its pipe menus."
PROVIDES = "obmenu"
#DEPENDS += "startup-notification gtk+3"
RDEPENDS_${PN} += "openbox"

SRC_URI = " \
	${SOURCEFORGE_MIRROR}/${PN}/${PN}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "710036a5edc9886d6d563ce46c747432"
SRC_URI[sha256sum] = "c9cea64d40d461a34ea3bdaec9867487c819d5f9814e1cc54db607d4d3c20e27"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9c81bd31a37abe2f452daab1f76bd783"
S = "${WORKDIR}/${PN}-${PV}/"

inherit pkg_distribute

