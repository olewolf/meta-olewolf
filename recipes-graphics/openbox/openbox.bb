MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://openbox.org"
SUMMARY = "standards-compliant, fast, light-weight and extensible window manager"
DESCRIPTION = "Openbox is a window manager that is fully functional as a stand-alone working environment, or can be used as a drop-in replacement for the default window manager in the GNOME or KDE desktop environments."
PROVIDES = "openbox"
#RRECOMMENDS_${PN} += "obconf"
DEPENDS += "libxml2 glib-2.0 pango"

SRC_URI = " \
	git://git.openbox.org/dana/openbox \
"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
S = "${WORKDIR}/git/"

PACKAGES =+ "${PN}-themes"
FILES_${PN}-themes += "${datadir}/themes"
FILES_${PN} += "${datadir}"

inherit gettext autotools

