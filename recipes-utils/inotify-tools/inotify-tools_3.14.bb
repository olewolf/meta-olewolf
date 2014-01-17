MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "https://github.com/rvoicilas/inotify-tools/wiki"
SUMMARY = "command-line programs providing a simple interface to inotify"
DESCRIPTION = "inotify-tools is a C library and a set of command-line programs for Linux providing a simple interface to inotify."
PROVIDES = "inotify-tools"

SRC_URI = " \
	git://github.com/rvoicilas/inotify-tools.git \
"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ac6c26e52aea428ee7f56dc2c56424c6"
S = "${WORKDIR}/git/"

inherit autotools

