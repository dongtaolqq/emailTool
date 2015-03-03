#!/bin/sh
currentPath=`pwd`
if [ ! -x "${currentPath}/backlib" ]; then
	mkdir ${currentPath}/backlib
fi
mv lib/org.eclipse.swt.win32.win32.x86_64-4.3.jar backlib/
PATH=$PATH:$currentPath/jre6/bin
java -XstartOnFirstThread -classpath classes:lib/* org.wx.Application