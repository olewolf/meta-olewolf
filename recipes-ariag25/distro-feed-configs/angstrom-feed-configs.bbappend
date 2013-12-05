ANGSTROM_URI = "http://opkg.blazingangles.com"
FEED_BASEPATH = "stable"

EXTRA_ARCHS_${PN} = "all armv5te arm926ejste at91ariag25"

FILES_${PN} += " \
	${sysconfdir}/opkg/base-feed.conf \
	${sysconfdir}/opkg/all-feed.conf \
	${sysconfdir}/opkg/armv5te-feed.conf \
	${sysconfdir}/opkg/arm926ejste-feed.conf \
	${sysconfdir}/opkg/at91ariag25-feed.conf \
"


do_compile() {
	mkdir -p ${S}/${sysconfdir}/opkg

	for FEED in ${EXTRA_ARCHS_${PN}}; do
		echo "src/gz ${FEED} ${ANGSTROM_URI}/${FEED_BASEPATH}/${FEED}" > ${S}/${sysconfdir}/opkg/${FEED}-feed.conf
	done

	echo "src/gz base ${ANGSTROM_URI}/${FEED_BASEPATH}" > ${S}/${sysconfdir}/opkg/base-feed.conf
}
