# Linux kernel recipe that is decoupled from the "true" OpenEmbedded kernel
# build method because for some reason it causes the USB host to not work.

MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.kernel.org"
SUMMARY = "Linux kernel"
DESCRIPTION = ""

PROVIDES = " \
	virtual/kernel \
"
PR = "r1"

KERNEL_MAJOR = "3"
KERNEL_MINOR = "12"
KERNEL_REVISION = "2"

KERNEL_IMAGETYPE = "uImage"

##############################################################################

SRC_URI = " \
	git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;tag=v${KERNEL_MAJOR}.${KERNEL_MINOR}.${KERNEL_REVISION} \
	file://defconfig \
	file://${MACHINE}.dts \
"
S = "${WORKDIR}/git/"
SRCREV = "${AUTOREV}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PACKAGES += "${PN}-modules ${PN}-devicetree"
FILES_${PN}-modules = "/lib/modules/*"
FILES_${PN}-devicetree = "/boot/*.dtb"
FILES_${PN} += "/boot/${KERNEL_IMAGETYPE}"

inherit autotools
addtask do_deploy after do_install before do_rm_work

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

do_install () {
	make ${PARALLEL_MAKE} CFLAGS="${CFLAGS}" CC="${CC}" modules_install ARCH=${TARGET_ARCH} CROSS_COMPILE=${TOOLCHAIN} INSTALL_MOD_PATH=${D}/
	install -m 0600 -D ${S}arch/${TARGET_ARCH}/boot/${KERNEL_IMAGETYPE} ${D}/boot/${KERNEL_IMAGETYPE}
	install -m 0600 -D ${S}arch/${TARGET_ARCH}/boot/dts/${MACHINE}.dtb ${D}/boot/${MACHINE}.dtb
	exit 0
}

do_deploy () {
	IMAGE_VERSION="${KERNEL_MAJOR}.${KERNEL_MINOR}.${KERNEL_REVISION}"
	mkdir -p ${DEPLOY_DIR}/images/${MACHINE}
	cp ${S}arch/${TARGET_ARCH}/boot/${KERNEL_IMAGETYPE} ${DEPLOY_DIR}/images/${MACHINE}/${KERNEL_IMAGETYPE}-${IMAGE_VERSION}
	cp ${S}arch/${TARGET_ARCH}/boot/dts/${MACHINE}.dtb ${DEPLOY_DIR}/images/${MACHINE}/${PN}-devicetree-${MACHINE}-${IMAGE_VERSION}.dtb
	tar -cvaf ${DEPLOY_DIR}/images/${MACHINE}/${PN}-modules-${IMAGE_VERSION}.tar.xz -C ${D} lib

	rm -f ${DEPLOY_DIR}/images/${MACHINE}/${KERNEL_IMAGETYPE}
	ln -s ${KERNEL_IMAGETYPE}-${IMAGE_VERSION} ${DEPLOY_DIR}/images/${MACHINE}/${KERNEL_IMAGETYPE}
	rm -f ${DEPLOY_DIR}/images/${MACHINE}/${PN}-modules.tar.xz
	ln -s ${PN}-modules-${IMAGE_VERSION}.tar.xz ${DEPLOY_DIR}/images/${MACHINE}/${PN}-modules.tar.xz
	rm -f ${DEPLOY_DIR}/images/${MACHINE}/${MACHINE}.dtb
	ln -s ${PN}-devicetree-${MACHINE}-${IMAGE_VERSION}.dtb ${DEPLOY_DIR}/images/${MACHINE}/${MACHINE}.dtb
	exit 0
}
