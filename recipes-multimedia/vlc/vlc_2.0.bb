MAINTAINER = "Ole Wolf <ole@naturloven.dk>"
HOMEPAGE = "http://www.videolan.org"
SUMMARY = "multimedia player and streamer"
DESCRIPTION = "VLC is the VideoLAN project's media player.  It plays MPEG, MPEG-2, MPEG-4, DivX, MOV, WMV, QuickTime, WebM, FLAC, MP3, Ogg/Vorbis files, DVDs, VCDs, podcasts, and multimedia streams from various network sources.  VLC can also be used as a streaming server that duplicates the stream it reads and multicasts them through the network to other clients, or serves them through HTTP."
PROVIDES = "vlc"
DEPENDS += "libdvdread faad2 flac libvorbis schroedinger libmad x264 libass \
	virtual/libsdl qt4-x11-free libxml2 libxpm fluidsynth libvpx gtk+ \
	gnutls dbus libtool libfribidi ffmpeg alsa-lib libgcrypt gst-plugins-bad \
	libdvdread lua5.1 lua5.1-native libupnp liba52 \
"
# Search for:
#
#	libvcdinfo libavcodec libwma-fixed libmpeg2 libx262 libx265 libswscale
#	libcaca - for ASCII art
#	libdvdnav - for DVD navigation
#	libupnp - (gupnp doesn't suffice)
#RDEPENDS_${PN} += "libdvdread faad2 flac libvorbis schroedinger libmad x264 libass libvpx \
#"

# Don't use Thumb instructions in speed-optimized apps.
ARM_INSTRUCTION_SET = "arm"
LEAD_SONAME = "libvlc.so.5"

SRC_URI = "git://git.videolan.org/vlc.git"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools gettext

do_configure_prepend () {
	./bootstrap
}

EXTRA_OECONF = " \
    --enable-dvdread \
    --with-contrib \
    --enable-run-as-root \
    --enable-xvideo \ 
    --disable-screen --disable-caca \
    --enable-httpd --enable-vlm \
    --enable-freetype \
    --enable-sdl \ 
    --enable-png \
    --enable-v4l2 --enable-v4l --disable-aa --disable-faad \
	--disable-live555 \
	--enable-tremor \
    --enable-dbus --enable-udev \
	--enable-upnp \
    --without-contrib \
    --without-kde-solid \
    --disable-opengl --disable-glx \
    --enable-realrtsp \
    ac_cv_path_MOC=${STAGING_BINDIR_NATIVE}/moc4 \
    ac_cv_path_RCC=${STAGING_BINDIR_NATIVE}/rcc4 \
    ac_cv_path_UIC=${STAGING_BINDIR_NATIVE}/uic4 \
"
#--enable-static-plugins
#--enable-libxml2=no
#--enable-live555 --enable-tremor
#--enable-upnp

PACKAGECONFIG[mad] = "--enable-mad,--disable-mad,libmad"
PACKAGECONFIG[id3tag] = "--enable-id3tag,--disable-id3tag,libid3tag"
PACKAGECONFIG[a52] = "--enable-a52,--disable-a52,liba52"
PACKAGECONFIG[mpeg2dec] = "--enable-mpeg2dec,--disable-mpeg2dec,mpeg2dec"

do_configure() {
    cp ${STAGING_DATADIR}/aclocal/libgcrypt.m4 ${S}/m4/
    ./bootstrap
    gnu-configize --force
    libtoolize --force
#    #autoreconf --force -i
    cp ${STAGING_DATADIR}/libtool/config.* ${S}/autotools/ || true
    oe_runconf
    rm config.log
#    sed -i -e s:-L/usr/lib:-L${STAGING_LIBDIR}/:g vlc-config
    sed -i -e s:'$(MOC) $(DEFS) $(CPPFLAGS)':'$(MOC) $(DEFS)'\ -I${S}/include\ -DSYS_LINUX:g ${S}/modules/gui/qt4/Makefile
}
