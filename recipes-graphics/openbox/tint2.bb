MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://"
SUMMARY = "Lightweight taskbar"
DESCRIPTION = "Tint is a simple taskbar for Openbox which includes transparency and color options for tet, icons, borders, and background.  Tint also supports multiple desktops and a built-in clock."
PROVIDES = "tint2"
DEPENDS += "imlib2 glib-2.0 pango xinerama gtk+-x11-2.0"
#DEPENDS += "startup-notification gtk+3"
#RDEPENDS_${PN} += ""

SRC_URI = " \
	https://tint2.googlecode.com/files/tint2-0.11.tar.bz2 \
"
SRC_URI[md5sum] = "6fc5731e7425125fa84a2add5cef4bff"
SRC_URI[sha256sum] = "fe106e6a6057d2631abddde9f82d3fd4fb1985c4fb93f10d3886417a9e22471d"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
S = "${WORKDIR}/tint2-0.11/"

inherit cmake

