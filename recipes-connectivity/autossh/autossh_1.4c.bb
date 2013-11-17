MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.harding.motd.ca/autossh/"
SUMMARY = "Automatically restart SSH sessions and tunnels"
DESCRIPTION = "autossh is a program to start a copy of ssh and monitor it, restarting it as necessary should it die or stop passing traffic. The idea is from rstunnel (Reliable SSH Tunnel), but implemented in C."
PROVIDES = "autossh"

SRC_URI = " \
	http://www.harding.motd.ca/autossh/autossh-1.4c.tgz \
"

SRC_URI[md5sum] = "26520eea934f296be0783dabe7fcfd28"
LICENSE = "Distributable"
LIC_FILES_CHKSUM = "file://autossh.spec;md5=80a6701134723fd3420e733b46a0eb97"
S = "${WORKDIR}/autossh-1.4c"

RDEPENDS_${PN} += "ssh"

FILES_${PN} = " \
"
#	${bindir}/autossh 

#EXTRA_OEMAKE = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" DESTDIR=${D}'

#do_compile () {
#	RPM_OPT_FLAGS="0" oe_runmake -C unix-console
#}

#do_install () {
#	DESTDIR=${D} oe_runmake install
#
#	install -m 0755 -d ${D}${sysconfdir}
#	install -m 0600 ${S}/doc/aiccu.conf ${D}${sysconfdir}
#	install -m 0755 -d ${D}${sysconfdir}/init.d
#	install -m 0755 ${S}/doc/aiccu.init.debian ${D}${sysconfdir}/init.d/aiccu
#}
