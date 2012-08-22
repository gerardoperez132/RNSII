#!/bin/bash
REMOTES=$(git remote)
for i in $REMOTES;
do
  printf '\n'
  echo $(tput bold)"Branch => $1..."$(tput sgr0)
  echo $(tput bold)"Uploading changes to $i please wait..."$(tput sgr0)
  git push $i $1
done
