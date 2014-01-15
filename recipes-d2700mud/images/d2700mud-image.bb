DESCRIPTION = "In-vehicle entertainment system using systemd and opkg."

export IMAGE_BASENAME = "d2700mud-image"
LICENSE = "GPLv3+"
PR = "r0"

inherit core-image
DISTRO_TYPE = "debug"

WINDOWS_MANAGER = " \
	openbox \
	obconf \
	obmenu \
	tint2 \
"

# Configure the package manager.
FEED_CONFIGS = "angstrom-feed-configs"
EXTRAOPKGCONFIG = "opkg-config-base ${FEED_CONFIGS}"
DISTRO_UPDATE_ALTERNATIVES ?= "opkg"
# Use the same provider for opkg and update-alternatives.
PREFERRED_PROVIDER_virtual/update-alternatives-native ?= "opkg-native"
PREFERRED_PROVIDER_virtual/update-alternatives = "${DISTRO_UPDATE_ALTERNATIVES}"
VIRTUAL-RUNTIME_update-alternatives = "${DISTRO_UPDATE_ALTERNATIVES}"
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'
PREFERRED_PROVIDER_ssh ?= "dropbear"

# Configure connection manager.
CONMANPKGS ?= " \
	connman connman-systemd \
	connman-angstrom-settings \
	connman-plugin-loopback \
	connman-plugin-ethernet \
	connman-plugin-wifi \
	connman-plugin-g3 \
"
MACHINE_FEATURES = " \
	ext3 ext4 vfat \
	serial \
	usbhost \
	can \
	apm \
	touchscreen \
	rtc \
	x86 \
	acpi \
"
# Additional machine features include:  kernel26 redboot pci apex screen wifi sgx irda phone gps keyboard compass gsensor lightsensor ext2 nfs usbgadget mmcbluetooth sdio gpio camera ethernet



DISTRO_FEATURES += " \
	systemd \
	ext3 ext4 \
	alsa \
	ipv6 ipsec \
	usbhost \
	base-passwd \
"
# Additional features include:  nfs irda usbgadget


SERIAL_CONSOLE = "115200 ttyS0"

PROVIDES_${PN} += "task-boot"
RREPLACES_${PN} += "task-boot"
RCONFLICTS_${PN} += "task-boot"
RDEPENDS_${PN} = " \
	${@base_contains("MACHINE_FEATURES", "keyboard", "keymaps", "", d)} \
	${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
"
IMAGE_DEV_MANAGER              = "udev"
VIRTUAL-RUNTIME_dev_manager    = "udev"
IMAGE_INIT_MANAGER             = "systemd"
VIRTUAL-RUNTIME_init_manager   = "systemd"
#IMAGE_LOGIN_MANAGER           = "busybox"
#VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_initscripts    = ""
IMAGE_LINGUAS                  = "en-us da-dk"

IMAGE_FEATURES += " \
	package-management \
	ssh-server-dropbear \
	hwcodecs \
	debug-tweaks \
"

IMAGE_INSTALL = "\
	task-core-boot \
	${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
	${CORE_IMAGE_BASE_INSTALL} \
	${ROOTFS_PKGMANAGE_PKGS} \
	systemd \
	kernel-modules \
	${CONMANPKGS} \
	cpufreq-tweaks \
	avahi-daemon \
	wget \
	bash \
	vim \
	${WINDOWS_MANAGER} \
	usb-modeswitch \
"
# emacs breaks the system because qemu doesn't work.

EXTRA_IMAGEDEPENDS += " \
	syslinux \
"

IMAGE_PREPROCESS_COMMAND += "rootfs_update_timestamp"
