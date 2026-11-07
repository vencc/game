@echo off
setlocal EnableExtensions EnableDelayedExpansion
pushd "%~dp0"

where javac >nul 2>nul
if errorlevel 1 goto no_jdk
where java >nul 2>nul
if errorlevel 1 goto no_java

for /f "tokens=2" %%V in ('javac -version 2^>^&1') do set "JAVA_VERSION=%%V"
if not defined JAVA_VERSION goto no_version
for /f "tokens=1,2 delims=." %%A in ("%JAVA_VERSION%") do (
  if "%%A"=="1" (
    set "JAVA_MAJOR=%%B"
  ) else (
    set "JAVA_MAJOR=%%A"
  )
)
if %JAVA_MAJOR% LSS 8 goto old_java

set "JAR_DIR=%~dp0resource\lib"
set "LIB_CP=%JAR_DIR%\JTattoo.jar;%JAR_DIR%\dsn.jar;%JAR_DIR%\gimap.jar;%JAR_DIR%\imap.jar;%JAR_DIR%\mailapi.jar;%JAR_DIR%\pop3.jar;%JAR_DIR%\smtp.jar"
for %%J in (JTattoo.jar dsn.jar gimap.jar imap.jar mailapi.jar pop3.jar smtp.jar) do (
  if not exist "%JAR_DIR%\%%J" (
    echo Missing required library: resource\lib\%%J
    goto failed
  )
)

if not exist "bin" mkdir "bin"
set "SOURCE_FILES="
for /r "src" %%F in (*.java) do set "SOURCE_FILES=!SOURCE_FILES! "%%F""
if not defined SOURCE_FILES (
  echo No Java source files were found in src.
  goto failed
)

echo Compiling the game...
javac -encoding UTF-8 -cp "%LIB_CP%" -d "bin" !SOURCE_FILES!
set "RESULT=%ERRORLEVEL%"
if not "%RESULT%"=="0" (
  echo.
  echo Game compilation failed. Fix the errors above, then run this file again.
  goto failed
)

echo Starting the game...
java -cp "bin;%LIB_CP%" chess.Main
set "RESULT=%ERRORLEVEL%"
if not "%RESULT%"=="0" (
  echo.
  echo The game closed with error code %RESULT%.
  goto failed
)
goto end

:no_jdk
echo A Java Development Kit (JDK) version 8 or later is required.
echo Install a JDK, then reopen this launcher so javac is available on PATH.
goto failed

:no_java
echo Java was not found on PATH. Install a JDK version 8 or later and try again.
goto failed

:no_version
echo Could not determine the JDK version. Make sure a JDK version 8 or later is on PATH.
goto failed

:old_java
echo JDK version 8 or later is required. Detected version: %JAVA_VERSION%
goto failed

:failed
echo.
pause
goto end

:end
popd
endlocal
