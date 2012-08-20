REMOTES=$(git remote)
for i in $REMOTES;
do
  echo $(tput bold)"\nUploading changes to $i please wait..."$(tput sgr0)
  git push $i permisos
done
