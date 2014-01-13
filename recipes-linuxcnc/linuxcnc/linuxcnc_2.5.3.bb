MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.linuxcnc.org"
SUMMARY = "software system for computer control of milling machines and lathes"
DESCRIPTION = "LinuxCNC is a software system for computer control of machines such as milling machines, lathes, plasma cutters, cutting machines, robots, hexapods, etc."
PROVIDES = "linuxcnc"
DEPENDS += "libmodbus rtai libgtk-2.0"

SRC_URI = " \
	git://git.linuxcnc.org/git/linuxcnc.git \
"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5ad41ed7aac91d2ffb194c9fc1d45ed8"
S = "${WORKDIR}/git/src/"

inherit autotools gettext

# libpth-dev tcl-dev tk-dev tcl8.4-dev tk8.4-dev bwidget libxaw7-dev libncurses-dev libreadline-dev python-dev python-tk python-lxml libglu1-mesa-dev libgl1-mesa-swx11-dev libgtk2.0-dev libgnomeprintui2.2-dev libboost-python-dev libmodbus-dev python-support
# texlive-lang-cyrillic texlive-lang-french texlive-lang-german texlive-lang-spanish texlive-lang-polish dvipng asciidoc source-highlight dblatex groff 
#  autoconf
