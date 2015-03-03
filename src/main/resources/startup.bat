@echo off
set currentPath=%cd%
if not exist %currentPath%\backlib mkdir %currentPath%\backlib
move %currentPath%\lib\org.eclipse.swt.cocoa.macosx.x86_64-4.3.jar %currentPath%\backlib

set path=%path%;%cd%\jre6\bin

cd %current_path%
java -classpath lib\*;classes org.wx.Application