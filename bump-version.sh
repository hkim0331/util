#!/usr/bin/env bash

if [ -z "$1" ]; then
    echo "usage: $0 <version>"
    exit
fi

# using extended regular expressions in this script, so,
if [ -x "${HOMEBREW_PREFIX}/bin/gsed" ]; then
    SED="${HOMEBREW_PREFIX}/bin/gsed -E"
else
    SED="/usr/bin/sed -E"
fi

# CHANGELOG.md
VER=$1
TODAY=`date +%F`
${SED} -i.bak -e "/SNAPSHOT/c\
## ${VER} / ${TODAY}" CHANGELOG.md

# build.clj
${SED} -i.bak "s/(def version) .+/\1 \"$1\")/" build.clj

# README.md, Dependency
# FIXME: VER is updated after commiting.
# SHA=`git rev-parse --short $VER^{commit}`
# ${SED} -i.bak "/:deps/c \
# :deps io.github.hkim0331/util {:git/tag $VER :git/sha $SHA}" README.md


