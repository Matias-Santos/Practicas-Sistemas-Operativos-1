#!/bin/bash
echo $(cat $1 | tr 0-9 $2) > $1