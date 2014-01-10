MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.gnome.org"
SUMMARY = "GTK+ graphica user interface library"
DESCRIPTION = "GTK+ is a multi platform tool for creating graphical user interfaces."
PROVIDES = "minidlna"
DEPENDS += "libav"
#libavutil ffmpeg

SRC_URI = " \
	git://git.code.sf.net/p/minidlna/git \
"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.miniupnpd;md5=b0dabf9d8e0f871554e309d62ead8d2b"

inherit autotools

