#!/bin/zsh
echo building l version...
convert "${1}_xl.png" -resize 50% "${1}_l.png"
echo building m version...
convert "${1}_l.png" -resize 50% "${1}_m.png"
echo building s version...
convert "${1}_m.png" -resize 50% "${1}_s.png"
echo building xs version...
convert "${1}_s.png" -resize 50% "${1}_xs.png"
