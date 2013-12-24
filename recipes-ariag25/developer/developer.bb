SUMMARY = "Add 'developer' user and give him/her sudo privileges"
DESCRIPTION = "Add a user named 'developer' with password 'developer' and create an entry in /etc/sudoers.d that provides this user with sudo privileges on any command."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://gpl.txt;md5=d32239bcb673463ab874e80d47fae504"
MAINTAINER = "Ole Wolf <ole@naturloven.dk>"

PR = "r0"
PROVIDES = "developer"
DEPENDS += "sudo"
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

${sbindir}/useradd -m -d /home/developer -s /bin/ash -c "System Developer" -U developer
echo "developer:developer" | ${sbindir}/chpasswd
}

pkg_postrm_${PN}_append () {
#!/bin/sh

${sbindir}/userdel -r developer || true
${sbindir}/groupdel -r developer || true
}
