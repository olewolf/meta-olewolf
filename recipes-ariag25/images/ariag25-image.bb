DESCRIPTION = "Minimal image with opkg."
require recipes-images/angstrom/console-base-image.bb

export IMAGE_BASENAME = "ariag25-image"

LICENSE = "GPLv3+"
PR = "r0"

IMAGE_PREPROCESS_COMMAND = "rootfs_update_timestamp"

#DISTRO_UPDATE_ALTERNATIVES ??= ""
#ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

#IMAGE_FEATURES += "package-management"

#CONMANPKGS ?= "connman connman-angstrom-settings connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-systemd"
#CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-systemd"

RDEPENDS_${PN} += " \
	at91bootstrap \
	u-boot \
"
#	util-linux-libuuid \
#	systemd \
#	busybox \
#"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
#IMAGE_LOGIN_MANAGER = "tinylogin shadow"

#	${CORE_IMAGE_BASE_INSTALL} 
#	coreutils

#IMAGE_INSTALL += "\
#	${ROOTFS_PKGMANAGE_PKGS} \
#	${CONMANPKGS} \
#	dropbear \
#"

#	bash \
#	emacs \
#	wget \
#	cpufreq-tweaks \
#	denyhosts \
#	bitcoind \
#	tor \
#	privoxy \
#	aiccu \
#

#inherit core-image
#inherit image

