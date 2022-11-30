#!/bin/sh

echo "Script Start."

# 배경을 회색(47), 문자색을 빨강(31)로 변경
# 이스케이프 시퀀스 문법
# \033[파라미터m 표시할 문자열 \033[0m
echo "\033[47;31m Important Message \033[0m"

echo "Script End."
