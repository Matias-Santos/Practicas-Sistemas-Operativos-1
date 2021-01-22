#!/bin/bash

contador=0
puntosEquipo1=0
puntosEquipo2=0
setEquipo1=0
setEquipo2=0

for (( i=2; i <= 6; i++ )); do
    set=$(cat $1 | head -$i | tail -1)
    for puntos in $set; do
        if (( contador >= 1 && contador <= 2 )); then
            let puntosEquipo1+=$puntos
        else
            if (( contador >= 3 && contador <= 4 )); then
                let puntosEquipo2+=$puntos
            fi
        fi
        let contador+=1
    done
    contador=0
    if (( $puntosEquipo1 < $puntosEquipo2 )); then
        let setEquipo2+=1
    else 
        if (( $puntosEquipo1 > $puntosEquipo2 )); then
            let setEquipo1+=1
        fi
    fi
    puntosEquipo1=0
    puntosEquipo2=0
done

if (( $setEquipo1 < $setEquipo2 )); then
    echo "Gano el equipo 2"
else
    if (( $setEquipo1 > $setEquipo2 )); then
        echo "Gano el equipo 1"
    else 
        echo "Se empato"
    fi
fi