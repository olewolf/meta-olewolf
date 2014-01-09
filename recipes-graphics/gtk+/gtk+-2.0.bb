MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.gnome.org"
SUMMARY = "GTK+ graphica user interface library"
DESCRIPTION = "GTK+ is a multi platform tool for creating graphical user interfaces."
PROVIDES = "gtk+-2.0"
#DEPENDS += " \
#	automake-native autoconf-native libtool-native \
#	glib-2.0 xinerama cups \
#"
DEPENDS += "glib-2.0 xinerama cups"

GTK_VERSION_MAJOR = "2"
GTK_VERSION_MINOR = "24"

# Via git:
#SRC_URI = " \
#	git://git.gnome.org/gtk+;branch=gtk-${GTK_VERSION_MAJOR}-${GTK_VERSION_MINOR} \
#"
#SRCREV = "${AUTOREV}"
#S = "${WORKDIR}/git/"

GTK_REVISION = "22"
SRC_URI = " \
	http://ftp.gnome.org/pub/gnome/sources/gtk+/2.24/gtk+-${GTK_VERSION_MAJOR}.${GTK_VERSION_MINOR}.${GTK_REVISION}.tar.xz \
"
SRC_URI[md5sum] = "5fbbfb7637bbd571a572a2dae0e736d2"
SRC_URI[sha256sum] = "b114b6e9fb389bf3aa8a6d09576538f58dce740779653084046852fb4140ae7f"
S = "${WORKDIR}/${PN}-${GTK_VERSION}/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit autotools gettext

EXTRA_OEFLAGS += "--disable-glibtest"


#do_configure () {
#	aclocal
#	autoconf
#	./autogen.sh ${EXTRA_OEFLAGS}
#	chmod 755 ./-libtool
#}
