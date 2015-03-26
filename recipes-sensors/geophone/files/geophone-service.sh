#!/bin/sh

PORT=/dev/ttyACM0
LOG_FILE=/var/lib/geophone/geophone.log

. /etc/default/geophone

/usr/bin/geophone-log-serial $PORT >> $LOG_FILE
