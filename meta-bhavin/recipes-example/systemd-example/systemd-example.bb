SUMMARY = "A Example of a recipe that utilizes systemd"
DESCRIPTION = "Runs sysd.sh script using a systemd service"

LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://sysd.sh \
            file://sysd.service "

inherit systemd

# black@bhavin:~/openbmc/build/qemuarm$ bitbake -e systemd-example | grep ^WORKDIR=
# WORKDIR="/home/black/openbmc/build/qemuarm/tmp/work/cortexa15t2hf-neon-openbmc-linux-gnueabi/systemd-example/1.0"

S = "${WORKDIR}/sources-unpack"

# black@bhavin:~/openbmc/build/qemuarm$ bitbake -e systemd-example | grep ^S=
# S="/home/black/openbmc/build/qemuarm/tmp/work/cortexa15t2hf-neon-openbmc-linux-gnueabi/systemd-example/1.0/sources-unpack"

RDEPENDS:${PN} = "bash"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "sysd.service"

# black@bhavin:~/openbmc/build/qemuarm$ bitbake -e systemd-example | grep ^D=
# D="/home/black/openbmc/build/qemuarm/tmp/work/cortexa15t2hf-neon-openbmc-linux-gnueabi/systemd-example/1.0/image"
# bindir is user bin 

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/sysd.sh ${D}${bindir}

#   sysconfdir = /etc i.e. .service file will be under /etc/systemd/system
    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${S}/sysd.service ${D}${sysconfdir}/systemd/system
}