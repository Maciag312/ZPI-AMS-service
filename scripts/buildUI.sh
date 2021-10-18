cd ../ZPI-AMS-UI
npm install
npm run build
mkdir -p ../ZPI-AMS-service/src/main/resources/static
rm -r ../ZPI-AMS-service/src/main/resources/static/*
cp -a ./build/. ../ZPI-AMS-service/src/main/resources/static/
