#!/bin/sh

username=guest
hostname=localhost

printf "Password: "

stty -echo
read password

stty echo

echo

#wget -q -password="$password" "ftp://${username}@${hostname}/filename.txt"

#curl -s -u "${username}:${password}" -0 "ftp://${hostname}/filename.txt"
