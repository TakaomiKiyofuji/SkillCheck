@echo off
javac DenormalizeTSV.java
java DenormalizeTSV < input.tsv > output.tsv
pause
