#!/bin/bash

while read i; do 
    a=$( echo "$i" | cut -d "_" -f2 );
    b=$( echo "$i" | cut -d "_" -f3 );
    c=$( echo "$i" | cut -d "." -f4 ); 
    echo $a; 
    if [[ $(ls | grep $a) == "" ]]; then 
        mkdir $a; 
    fi;    
    cd $a;
    if [[ $(ls | grep $b) == "" ]]; then 
        mkdir $b;
    fi;
    cd $b;
    if [[ $(ls | grep $c) == "" ]]; then 
        mkdir $c;
    fi;
    cd $c;
    touch "$i";
    cd .. ;
    cd .. ;
    cd .. ;
done < test.txt