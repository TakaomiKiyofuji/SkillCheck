@echo off
javac NormalizeTSV.java
type input.tsv | java NormalizeTSV > output.tsv
pause