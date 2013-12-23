SUMMARY = "Add 'developer' user and give him/her sudo privileges"
DESCRIPTION = "Add a user named 'developer' and provide an entry in /etc/sudoers.d that provides the user with sudo privileges on any command."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://gpl.txt;md5=d32239bcb673463ab874e80d47fae504"
MAINTAINER = "Ole Wolf <ole@naturloven.dk>"

PR = "r0"
PROVIDES = "developer"
RDEPENDS_${PN} += "sudo"

SRC_URI = " \
	file://developer \
	file://gpl.txt \
"

S = "${WORKDIR}/"

do_install () {
	install -m 0400 -D ${S}developer ${D}${sysconfdir}/sudoers.d/developer
}

pkg_postinst_${PN}_append () {
#/!bin/sh

useradd -m -d /home/developer -s /bin/ash -c "System Developer" -U developer
echo "developer:developer" | chpasswd
}

pkg_postrm_${PN}_append () {
#!/bin/sh

userdel -r developer || true
groupdel -r developer || true
}
