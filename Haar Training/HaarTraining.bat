"Opencv bin\opencv_haartraining.exe" -data cascades -vec Vectors/facevector.vec -bg negative/bg.txt -npos 200 -nneg 200 -nstages 20 -mem 1024 -mode ALL -w 24 -h 24 
PAUSE
rem -nonsym
