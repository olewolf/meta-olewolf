# Linux kernel recipe that is decoupled from the "true" OpenEmbedded kernel
# build method because for some reason it causes the USB host to not work.

MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.kernel.org"
SUMMARY = "Linux kernel"
DESCRIPTION = ""

PROVIDES = " \
	virtual/kernel \
"
PR = "r0"

KERNEL_MAJOR = "3"
KERNEL_MINOR = "12"
KERNEL_REVISION = "2"

KERNEL_IMAGETYPE = "uImage"

# Download via git
SRC_URI = " \
	git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;tag=v${KERNEL_MAJOR}.${KERNEL_MINOR}.${KERNEL_REVISION} \
	file://defconfig \
	file://${MACHINE}.dts \
"
S = "${WORKDIR}/git/"
SRCREV = "${AUTOREV}"

# Download a compressed image
#SRC_URI = " \
#	https://www.kernel.org/pub/linux/kernel/v${KERNEL_MAJOR}.x/linux-${KERNEL_MAJOR}.${KERNEL_MINOR}.${KERNEL_REVISION}.tar.xz \
#	file://defconfig \
#	file://${MACHINE}.dts \
#"
#SRC_URI[md5sum] = "987ceca6afd3956673a1b17e329beb73"
#SRC_URI[sha256sum] = "d3f339a29fb4905d126856803517d717649ebda355c2977510d4fc62de8672a6"
#S = "${WORKDIR}/linux-${KERNEL_MAJOR}.${KERNEL_MINOR}.${KERNEL_REVISION}/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PACKAGES += "${PN}-modules"
FILES_${PN}-modules = "/lib/modules/*"
FILES_${PN} += "/boot/*"

do_menuconfig () {
	make menuconfig
	exit 0
}

do_configure () {
	TOOLCHAIN_PATH=""
	TOOLCHAIN="$(echo $CC | sed -n -e 's/^\(.\+\)gcc.*/\1/p')"

	cp ../defconfig arch/${TARGET_ARCH}/configs/${MACHINE}_defconfig
	cp ../${MACHINE}.dts arch/${TARGET_ARCH}/boot/dts/
	oe_runmake ${PARALLEL_MAKE} ARCH=${TARGET_ARCH} CROSS_COMPILE=${TOOLCHAIN} ${MACHINE}_defconfig
	oe_runmake ${PARALLEL_MAKE} ARCH=${TARGET_ARCH} CROSS_COMPILE=${TOOLCHAIN} ${MACHINE}.dtb

	exit 0
}

do_compile () {
	TOOLCHAIN_PATH=""
	TOOLCHAIN="$(echo $CC | sed -n -e 's/^\(.\+\)gcc.*/\1/p')"

	CFLAGS="${BUILD_CFLAGS}"
	make ${PARALLEL_MAKE} CFLAGS="${CFLAGS}" CC="${CC}" ${KERNEL_IMAGETYPE} ARCH=${TARGET_ARCH} CROSS_COMPILE=${TOOLCHAIN}
	make ${PARALLEL_MAKE} CFLAGS="${CFLAGS}" CC="${CC}" modules ARCH=${TARGET_ARCH} CROSS_COMPILE=${TOOLCHAIN}

	exit 0
}

do_install_append () {
	make ${PARALLEL_MAKE} CFLAGS="${CFLAGS}" CC="${CC}" modules_install ARCH=${TARGET_ARCH} CROSS_COMPILE=${TOOLCHAIN} INSTALL_MOD_PATH=${D}/
	install -m 0600 -D ${S}arch/${TARGET_ARCH}/boot/${KERNEL_IMAGETYPE} ${D}/boot/${KERNEL_IMAGETYPE}

	exit 0
}

do_deploy () {
	cp ${S}/boot/uImage ${DEPLOY_DIR}/
	tar -cvaf ${DEPLOY_DIR}/${PN}-modules.tar.xz -C ${D} lib
	exit 0
}
