#!/bin/bash
echo $(cat $1 | tr a-z A-Z) > $1