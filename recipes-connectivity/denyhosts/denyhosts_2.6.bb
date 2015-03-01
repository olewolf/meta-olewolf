MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://sourceforge.net/projects/denyhosts/"
SUMMARY = "Utility to help sys admins thwart SSH crackers"
DESCRIPTION = "DenyHosts is a program that automatically blocks SSH brute-force attacks by adding entries to /etc/hosts.deny.  It will also inform Linux administrators about offending hosts, attacked users and suspicious logins."
PROVIDES = "denyhosts"
DEPENDS += " \
	python \
"
RDEPENDS_${PN} += " \
	ssh \
	python \
"
PR = "r2"
RRECOMMENDS_${PN} += " \
	logrotate \
"

SRC_URI = " \
	http://downloads.sourceforge.net/project/denyhosts/denyhosts/2.6/DenyHosts-2.6.tar.gz \
	file://dh_reenable \
	file://initscript \
	file://logrotate-denyhosts \
	file://0001foreground-option.patch \
	file://0002daemon-control.patch \
	file://0003debianize-config.patch \
"
SRC_URI[sha256sum] = "5190ead13a7238e3ccf328cb3b71b16716e1c73939909a4f3fa6904ba58ddf7d"
SRC_URI[md5sum] = "fc2365305a9402886a2b0173d1beb7df"

S = "${WORKDIR}/${PN}-${PV}/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=393a5ca445f6965873eca0259a17f833"

#inherit pkg_distribute
addtask fixfoldername before do_patch after do_unpack

PACKAGES = "${PN} ${PN}-doc"
FILES_${PN} = " \
	${sbindir} \
	${sysconfdir} \
	${datadir}/${PN} \
	${localstatedir}/lib/${PN} \
"
FILES_${PN}-doc = "${docdir}/${PN}"

# Move the unpacked files into a directory with an all-lower case name.
do_fixfoldername () {
	if [ -d ${WORKDIR}/DenyHosts-${PV} ]; then
		mv ${WORKDIR}/DenyHosts-${PV}/* ${S}
		rmdir ${WORKDIR}/DenyHosts-${PV}
	fi
}

do_configure_prepend () {
	sed -i -e "s,/usr/share/denyhosts,${S}install/denyhosts," ${S}setup.py
}

do_install () {
	INSTALLDIR=${S}/install
	python setup.py install --prefix=${INSTALLDIR}

	# Executable.
	install -m 0755 -d -D ${D}${sbindir}
	install -m 0755 ${INSTALLDIR}/bin/denyhosts.py ${D}${sbindir}/denyhosts

	# Python module.
	install -m 0755 -d -D ${D}${datadir}/denyhosts
	find ${INSTALLDIR}/lib/python*/site-packages/DenyHosts-${PV}-py*.egg-info -exec install -m 0644 {} ${D}${datadir}/${PN}/DenyHosts-${PV}.egg-info \;
	install -m 0755 -d -D ${D}${datadir}/${PN}/DenyHosts
	for INSTALL_FILE in ${INSTALLDIR}/lib/python*/site-packages/DenyHosts/*.py*; do
		install -m 0644 ${INSTALL_FILE} ${D}${datadir}/${PN}/DenyHosts
	done

	# 3rd-party denyhosts reenable script.
	install -m 0755 ${WORKDIR}/dh_reenable ${D}${datadir}/${PN}/DenyHosts

	# Control and configuration scripts.
	install -m 0755 ${INSTALLDIR}/denyhosts/daemon-control-dist ${D}${datadir}/denyhosts/denyhosts_ctl.py
	install -m 0755 -d ${D}${sysconfdir}
	install -m 0640 ${INSTALLDIR}/denyhosts/denyhosts.cfg-dist ${D}${sysconfdir}/denyhosts.conf
	install -m 0755 -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/initscript ${D}${sysconfdir}/init.d/denyhosts

	# Logrotate.
	install -m 0755 -d -D ${D}${sysconfdir}/logrotate.d
	install -m 0644 -D ${WORKDIR}/logrotate-denyhosts ${D}${sysconfdir}/logrotate.d/denyhosts

	# Work directory.
	install -m 0755 -d -D ${D}${localstatedir}/lib
	install -m 0750 -d ${D}${localstatedir}/lib/${PN}

	# Documentation.
	install -m 0755 -d -D ${D}${docdir}/${PN}
	find ${INSTALLDIR}/denyhosts/{CHANGELOG,LICENSE,README}.txt -exec install -m 0644 {} ${D}${docdir}/${PN} \;
	install -m 0755 -d ${D}${docdir}/${PN}/examples/plugins
	install -m 0755 -d ${D}${docdir}/${PN}/examples/scripts
	find ${S}plugins -exec install -m 0644 -D {} ${D}${docdir}/${PN}/examples/plugins \;
	find ${S}scripts -exec install -m 0644 -D {} ${D}${docdir}/${PN}/examples/plugins \;
}

