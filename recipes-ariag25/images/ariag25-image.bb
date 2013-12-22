DESCRIPTION = "Minimal Aria G25 image using systemd and opkg."

export IMAGE_BASENAME = "ariag25-image"
LICENSE = "GPLv3+"
PR = "r0"

inherit core-image

# Configure the package manager.
FEED_CONFIGS = "amgstrom-feed-configs"
EXTRAOPKGCONFIG = "opkg-config-base ${FEED_CONFIGS}"
DISTRO_UPDATE_ALTERNATIVES ?= "opkg"
# Use the same provider for opkg and update-alternatives.
PREFERRED_PROVIDER_virtual/update-alternatives-native ?= "opkg-native"
PREFERRED_PROVIDER_virtual/update-alternatives = "${DISTRO_UPDATE_ALTERNATIVES}"
VIRTUAL-RUNTIME_update-alternatives = "${DISTRO_UPDATE_ALTERNATIVES}"
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

PROVIDES_${PN} += "task-boot"
RREPLACES_${PN} += "task-boot"
RCONFLICTS_${PN} += "task-boot"
RDEPENDS_${PN} = " \
	${@base_contains("MACHINE_FEATURES", "keyboard", "keymaps", "", d)} \
	${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
"

# Configure connection manager.
CONMANPKGS ?= " \
	connman connman-systemd \
	connman-plugin-loopback \
	connman-plugin-ethernet \
	connman-plugin-wifi	\
"

MACHINE_FEATURES += " \
	kernel26 \
	ext2 \
	ext3 \
	ext4 \
	vfat \
	serial \
	usbhost \
	usbgadget \
	mmc \
	ethernet \
	bluetooth \
	sdio \
	gpio \
	nfs \
	can \
	apm \
	camera \
	touchscreen \
"
# Additional machine features include:  redboot pci apex screen wifi sgx irda phone gps keyboard compass gsensor lightsensor rtc

DISTRO_FEATURES += " \
	systemd \
	ext4 \
	alsa \
	ipv6 ipsec \
	nfs \
	irda \
	usbhost usbgadget \
	base-passwd \
"
# Additional features include:  ext3

SERIAL_CONSOLE = "115200 ttyS0"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "busybox"
IMAGE_LINGUAS       = "en-us da-dk"

IMAGE_FEATURES += " \
	package-management \
	debug-tweaks \
	ssh-server-dropbear \
	hwcodecs \
"

IMAGE_INSTALL += "\
	task-core-boot \
	kernel-modules \
	${CORE_IMAGE_BASE_INSTALL} \
	${ROOTFS_PKGMANAGE_PKGS} \
	${MACHINE_ESSENTIAL_EXTRA_RDEPENDS}  \
	${CONMANPKGS} \
	systemd \
	update-alternatives \
	apparmor \
	cpufreq-tweaks \
	base-files \
	base-passwd \
	netbase \
	emacs \
	wget \
	denyhosts \
	bitcoind \
	tor privoxy \
	aiccu \
	busybox \
"

#	util-linux-libuuid
#	bash
#	coreutils


EXTRA_IMAGEDEPENDS += " \
	at91bootstrap \
	u-boot \
"

IMAGE_PREPROCESS_COMMAND += "create_etc_timestamp"

#CONMANPKGS ?= "connman connman-angstrom-settings connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-systemd"
#CONMANPKGS_libc-uclibc = ""
#IMAGE_PREPROCESS_COMMAND = "rootfs_update_timestamp"
