REMOTES=$(git remote)
for i in $REMOTES;
do
  echo "Updloading changes to $i please wait..."
  git push $i permisos
  echo "\n\n"
done
