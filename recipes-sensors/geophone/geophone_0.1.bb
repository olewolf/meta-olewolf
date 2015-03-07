# OpenEmbedded bitbake recipe for installing the geodata logger into an
# embedded Linux system.

MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "https://github.com/olewolf/geophone"
SUMMARY = "Geophone data logger and visualizer"
DESCRIPTION = "${SUMMARY}"
PROVIDES = "geophone-logger"

SRC_URI = " \
	git://github.com/olewolf/geophone.git;protocol=https \
	file://geophone-rotate-heatmap \
	file://geophone-rotate-heatmap.cron \
	file://geophone.logrotate \
	file://geophone.service \
"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94a3f3bdf61243b5e5cf569fbfbbea52"
S = "${WORKDIR}/git"

DEPENDS += " eglibc "
RDEPENDS_${PN} += " \
	logrotate \
	cronie \
	perl \
	imagemagick \
	nginx \
	liberation-fonts \
"
RRECOMMENDS_${PN} += " ntp "

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "geophone.service"

FILES_${PN} = " \
	/usr/bin/geophone-createheatmap \
	/usr/bin/geophone-filter-log \
	/usr/bin/geophone-rotate-heatmap \
	/usr/share/geophone/colormaps.png \
	/var/lib/geophone \
	/etc/logrotate.d/geophone \
	/etc/cron.d/geophone-rotate-heatmap \
"

PACKAGES =+ "${PN}-systemd"
FILES_${PN}-systemd = "${systemd_unitdir}/system/"
RDEPENDS_${PN}-systemd = "${PN}"

do_patch () {
	sed -i '/\$heatmap->Read( "colormaps.png" );/c\$heatmap->Read( "\/usr\/share\/geophone\/colormaps.png" );' ${S}/createheatmap.pl
}

do_compile () {
	CFLAGS="${CFLAGS} -std=gnu99 $(pkg-config --cflags glib-2.0)"
	LIBS="$(pkg-config --libs glib-2.0)"
	gcc $CFLAGS -o geophone-read-serial read-serial-log.c $LIBS
}

do_install () {
	install -m 0755 -d -D ${D}/usr/bin
	install -m 0755 ${S}/createheatmap.pl ${D}/usr/bin/geophone-createheatmap
	install -m 0755 ${S}/filter-log.pl ${D}/usr/bin/geophone-filter-log
	install -m 0755 ${WORKDIR}/geophone-rotate-heatmap ${D}/usr/bin/

	install -m 0755 -d -D ${D}/var/lib/geophone

	install -m 0755 -d -D ${D}/usr/share/geophone
	install -m 0644 ${S}/colormaps.png ${D}/usr/share/geophone/

	install -m 0755 -d -D ${D}/etc/logrotate.d
	install -m 0755 ${WORKDIR}/geophone.logrotate ${D}/etc/logrotate.d/geophone

	install -m 0755 -d -D ${D}/etc/cron.d
	install -m 0644 ${WORKDIR}/geophone-rotate-heatmap.cron ${D}/etc/cron.d/geophone-rotate-heatmap

	install -m 0755 -d -D ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/geophone.service ${D}/${systemd_unitdir}/system/
}
