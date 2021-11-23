cd ../ZPI-AMS-UI
#1/bin/bash
set -e -o pipefail

npm install
npm run build
mkdir -p ../ZPI-AMS-service/src/main/resources/static
rm -rf ../ZPI-AMS-service/src/main/resources/static/*
cp -a ./build/. ../ZPI-AMS-service/src/main/resources/static/
