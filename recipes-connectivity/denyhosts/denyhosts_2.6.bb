MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://sourceforge.net/projects/denyhosts/"
SUMMARY = "Utility to help sys admins thwart SSH crackers"
DESCRIPTION = "DenyHosts is a program that automatically blocks SSH \
 brute-force attacks by adding entries to /etc/hosts.deny. \
 It will also inform Linux administrators about offending \
 hosts, attacked users and suspicious logins."
PROVIDES = "denyhosts"
DEPENDS += " \
	python \
"
RDEPENDS_${PN} += " \
	ssh \
"
PR = "r0"

SRC_URI = " \
	http://downloads.sourceforge.net/project/denyhosts/denyhosts/2.6/DenyHosts-2.6.tar.gz \
"
SRC_URI[sha256sum] = "5190ead13a7238e3ccf328cb3b71b16716e1c73939909a4f3fa6904ba58ddf7d"
SRC_URI[md5sum] = "fc2365305a9402886a2b0173d1beb7df"

S = "${WORKDIR}/DenyHosts-${PV}/"
PACKAGES = "${PN}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=393a5ca445f6965873eca0259a17f833"

FILES_${PN} = " \
	/usr/share/denyhosts/*-dist \
	/usr/share/denyhosts/*py \
"

inherit setuptools

EXTRA_OECONF += " \
"

do_fetch_${PN}_append () {
	cd ${DL_DIR}
	mv download DenyHosts-2.6.tar.gz
	exit 0
}

do_unpack () {
	cd ${WORKDIR}
	tar -xvf ${DL_DIR}/DenyHosts-${PV}.tar.gz
	exit 0
}

#do_install () {
#	
#}
