#!/bin/bash
old_ifs=$IFS
IFS=$(echo -e "\n")
textoAux=$(cat "$1" | tr "," ' ' | tr "." " " | xargs | tr " " "\n" | tr A-Z a-z | sort)
let cant=0
let cantMax=0
texto=''
p=$(echo $textoAux | head -1)
IFS=$(echo -e "\n\b")
for palabra in $textoAux; do
    if [ $p == $palabra ]; then
        let cant+=1
    else
        texto+=$(echo -e "$p" "$cant" "\n\b")
        if [ $cant -gt $cantMax ]; then
            let cantMax=$cant
        fi
        let cant=1
        p=$palabra
    fi
done                                                                  #  $(echo "scale=2;$valor/$cantMax" | bc)
for line in $texto; do                                                #  $(echo $line | cut -d " " -f2)/$cantMax este es el numero flotante
    valor=$(echo $line | cut -d " " -f2)
    echo "TF(" $(echo $line | cut -d " " -f1 ) ") =" $valor/$cantMax "=" $(echo "scale=2;$valor/$cantMax" | bc -l) #El bc no anda, pero por el compilador de bash este, no porque esté mal
done
IFS=$old_ifs