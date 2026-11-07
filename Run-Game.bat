@echo off
setlocal EnableExtensions EnableDelayedExpansion
chcp 65001 >nul
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
    echo 필수 라이브러리가 없습니다: resource\lib\%%J
    goto failed
  )
)

if not exist "bin" mkdir "bin"
set "SOURCE_FILES="
for /r "src" %%F in (*.java) do set "SOURCE_FILES=!SOURCE_FILES! "%%F""
if not defined SOURCE_FILES (
  echo src 폴더에서 자바 소스 파일을 찾을 수 없습니다.
  goto failed
)

echo 게임을 컴파일하고 있습니다...
javac -encoding UTF-8 -cp "%LIB_CP%" -d "bin" !SOURCE_FILES!
set "RESULT=%ERRORLEVEL%"
if not "%RESULT%"=="0" (
  echo.
  echo.
  echo 게임 컴파일에 실패했습니다. 위 오류를 확인한 뒤 다시 실행해 주세요.
  goto failed
)

echo 게임을 실행합니다...
java -Dfile.encoding=UTF-8 -cp "bin;%LIB_CP%" chess.Main
set "RESULT=%ERRORLEVEL%"
if not "%RESULT%"=="0" (
  echo.
  echo.
  echo 게임이 오류 코드 %RESULT%로 종료되었습니다.
  goto failed
)
goto end

:no_jdk
echo JDK 8 이상이 필요합니다.
echo JDK를 설치한 뒤 javac를 PATH에 추가하고 이 파일을 다시 실행해 주세요.
goto failed

:no_java
echo PATH에서 Java를 찾을 수 없습니다. JDK 8 이상을 설치한 뒤 다시 실행해 주세요.
goto failed

:no_version
echo JDK 버전을 확인할 수 없습니다. JDK 8 이상이 PATH에 있는지 확인해 주세요.
goto failed

:old_java
echo JDK 8 이상이 필요합니다. 확인된 버전: %JAVA_VERSION%
goto failed

:failed
echo.
pause
goto end

:end
popd
endlocal
