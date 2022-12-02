#!/bin/sh

file="eval.sh"
eval $(sed -n "s/<code>\(.*\)<\/code>/\1/p" cmd.html)

