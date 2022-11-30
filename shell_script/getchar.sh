#!/bin/sh

printf "Type Your Answer [y/n]: "

# 현재 터미널 설정을 셸 변수 tty_state에 백업하고
# 터미널을 raw 설정함
tty_state=$(stty -g)
stty raw
char=$(dd bs=1 count=1 2> /dev/null)

# 터미널 설정을 원래대로 돌림
stty "$tty_state"

echo

# 입력된 문자에 따라 처리 분기
case "$char" in
    [yY])
        echo "Input: YES"
        ;;
    [nN])
        echo "Input: NO"
        ;;
    *)
        echo "Input: What?"
        ;;
esac