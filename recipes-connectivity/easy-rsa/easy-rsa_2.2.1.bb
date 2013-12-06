SUMMARY = "Simple shell based CA utility"
DESCRIPTION = "This package eases the creation of certificates, for example for openvpn clients.  This was formerly part of the openvpn package."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=e944ef975ef9d0312e63c9ee80df17fc"
MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "https://github.com/OpenVPN/easy-rsa"

PR = "r0"
PROVIDES = "easy-rsa"

SRC_URI = " \
	git://github.com/OpenVPN/easy-rsa.git;branch=release/2.x \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/"

FILES_${PN} += " \
	${datarootdir}/easy-rsa \
"

inherit autotools

