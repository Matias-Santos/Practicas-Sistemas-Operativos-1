#!/bin/bash

Parcial2015(){
    while read i|; do
        grep "Argentina" $i
    done < temperaturas.txt    
}