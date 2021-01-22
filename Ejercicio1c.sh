#!/bin/bash
cat -n $1 | sort -nr | cut --complement -f1 >  $1