rm -f file-modifier
sbt clean nativeLink
upx -9 file-modifier