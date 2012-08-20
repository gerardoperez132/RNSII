REMOTES=$(git remote)
for i in $REMOTES;
do
  git push $i permisos
done
#git push origin permisos
#git push github permisos
#git push cnti permisos
