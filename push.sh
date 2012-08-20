REMOTES=$(git remote)
for i in $REMOTES;
do
  echo $(tput bold)"\nUploading changes to $i please wait..."
  git push $i permisos
done
