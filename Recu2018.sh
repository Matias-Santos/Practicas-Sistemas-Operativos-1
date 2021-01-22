#!/bin/bash

function recu2018(){
    touch ParTexto2.txt;
    rm ParTexto2.txt;
    let b=0;
    let c=0;
    while read i; do
          let a=0;
          for word in $i ; do
              let a=$a+1;
          done;
          let b=$b+1;
          if (( $a <= $2 )); then
              echo $i >> ParTexto2.txt;
              let c=$c+1;
         fi;  
    done < $1
    echo cantidad lineas original: $b;
    echo cantidad lineas final: $c;
}