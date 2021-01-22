#!/bin/bash
old_ifs=$IFS
IFS=$(echo -e '\n\b')
nombresDeArchivos=$(ls | grep \.txt$)
for line in $nombresDeArchivos; do
    nombreModificado=$(echo "$line" | tr " " "_" | tr A-Z a-z )
    mv "$line" "$nombreModificado"
done
IFS=$old_ifs