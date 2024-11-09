java -cp vietdict.jar org.dict.server.DatabaseFactory data\GV\dv45K.ini dv
del data\GV\*.bak
java -cp vietdict.jar org.dict.server.DatabaseFactory data\FV\phapviet.ini fv
del data\FV\*.bak
java -cp vietdict.jar org.dict.server.DatabaseFactory data\NV\nv20K.ini nv
del data\NV\*.bak
pause
