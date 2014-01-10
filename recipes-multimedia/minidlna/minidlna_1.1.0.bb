MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://sourceforge.net/projects/minidlna/"
SUMMARY = "lightweight DLNA/UPnP-AV server targeted at embedded systems"
DESCRIPTION = "MiniDLNA (aka ReadyDLNA) is server software with the aim of being fully compliant with DLNA/UPnP-AV clients.  The minidlna daemon serves media files (music, pictures, and video) to clients on your network.  Example clients include applications such as totem and xbmc, and devices such as portable media players, smartphones, and televisions."
PROVIDES = "minidlna"
DEPENDS += "libav flac libexif libvorbis sqlite3 libid3tag"
RDEPENDS_${PN} += "libav flac libexif libvorbis sqlite3 libid3tag"

SRC_URI = "git://git.code.sf.net/p/minidlna/git"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/"
LICENSE = "MIT-style"
LIC_FILES_CHKSUM = "file://LICENCE.miniupnpd;md5=b0dabf9d8e0f871554e309d62ead8d2b"

inherit autotools gettext
