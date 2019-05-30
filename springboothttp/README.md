keytool -genkey 
        -alias tomcat 
        -storetype PKCS12 
        -keyalg RSA 
        -keysize 2048 
        -keystore keystore.p12 
        -validity 4000
        
        
        
vmOption -debug 모드 실행시 디버그 모드