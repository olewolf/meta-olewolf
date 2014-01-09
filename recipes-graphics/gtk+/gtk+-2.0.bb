MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.gnome.org"
SUMMARY = "GTK+ graphica user interface library"
DESCRIPTION = "GTK+ is a multi platform tool for creating graphical user interfaces."
PROVIDES = "gtk+-2.0"
DEPENDS += "glib-2.0"

SRC_URI = " \
	git://git.gnome.org/gtk+;branch=gtk-2-24 \
"
SRCREV = "${AUTOREV}"
#SRCREV = "d4538cf96c901ba852fa20f1b0b63bed91ab6d24"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
S = "${WORKDIR}/git/"

inherit autotools

