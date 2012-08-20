REMOTES=$(git remote)
for i in $REMOTES;
do
  echo "Updloading changes to $i please wait...\n"
  git push $i permisos
  echo "\n"
done
