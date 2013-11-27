MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "https://code.google.com/p/protobuf/"
SUMMARY = "Google protocol buffers"
DESCRIPTION = "Protocol Buffers are a way of encoding structured data in an efficient yet extensible format. Google uses Protocol Buffers for almost all of its internal RPC protocols and file formats."
PROVIDES = " libprotoc "
PR = "r0"

SRC_URI = " \
	https://protobuf.googlecode.com/files/protobuf-2.5.0.tar.bz2 \
"
SRC_URI[md5sum] = "a72001a9067a4c2c4e0e836d0f92ece4"
SRC_URI[sha256sum] = "13bfc5ae543cf3aa180ac2485c0bc89495e3ae711fc6fab4f8ffe90dfb4bb677"
# svn://protobuf.googlecode.com/svn/trunk/;module=protobuf-read-only;destsuffix=src
#SRCREV = "${AUTOREV}"

LICENSE = "Google"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=af6809583bfde9a31595a58bb4a24514"

S = "${WORKDIR}/protobuf-2.5.0/"

inherit autotools

#PROTOC = "`which protoc`"
# Protoc is outdated on Ubuntu 13.10, and may require a manual install of
# the current version.  If you know how to bitbake, then you know how to do
# that, too.
PROTOC = "/usr/local/bin/protoc"
EXTRA_OECONF = "--with-protoc=${PROTOC} --with-gnu-ld"
