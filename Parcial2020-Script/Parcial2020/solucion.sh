#!/bin/bash

nombreCarpeta=$(basename $1) #obtengo el nombre de la carpeta
#pregunto si el directorio que me envian existe
if [[ -d "$1" ]]; then
    #si existe, entonces entro en esa carpeta
    cd $nombreCarpeta
    #me fijo si tiene archivos dentro
    if [ "$(ls .)" ]; then
        #leo los archivos, así se que nombre deben de tener las carpetas a crear
        for archivos in $(ls -1 .); do
            linea=$(cat $archivos)
            contenido=''
            cambio=''
            for palabra in $linea; do
                if [ $palabra == $2 ]; then
                    cambio=$(echo $palabra | tr $2 $3)
                    cambio=$cambio' '
                else
                    cambio=$palabra' '
                fi
                contenido=$contenido$cambio
            done
            echo $contenido > $archivos
        done
    else
        echo "El directorio esta vacio"
    fi
else
    echo "El directorio no existe"
fi