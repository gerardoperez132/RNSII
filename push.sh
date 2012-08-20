REMOTES=$(git remote)
for i in $REMOTES;
do
  echo "\nUploading changes to $i please wait..."
  git push $i permisos
done
