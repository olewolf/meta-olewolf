MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://openbox.org"
SUMMARY = "Handle preferences in the Openbox windows manager"
DESCRIPTION = "obconf is a graphical tool for configuring the preferences and configuration of the Openbox windows manager."
PROVIDES = "obconf"
DEPENDS += "startup-notification gtk+3"
RDEPENDS_${PN} += "openbox startup-notification gtk+3"

SRC_URI = " \
	git://git.openbox.org/dana/obconf \
"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
S = "${WORKDIR}/git/"

inherit gettext autotools

FILES_${PN} += " \
	/usr/share/mime \
	/usr/share/mimelnk \
"

do_configure_prepend () {
	# Delete invalid entry in the LINGUAS file.
	sed -i '/en\@quot en\@boldquot/d' po/LINGUAS
}
