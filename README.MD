![Screenshot 2025-03-02 at 10.50.22 AM.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fc5%2Fmx3q05y52ql6x5s6jkr38kw40000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_c6We6M%2FScreenshot%202025-03-02%20at%2010.50.22%E2%80%AFAM.png)

JAVA 17

HOW TO START the application
run : 
    1. mvn clean install
    2. mvn spring-boot:run

API curl:

1. Register user:
   curl --location --request POST 'localhost:8081/users/register?username=headout' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
2. Get Random game Not played by the user:
   curl --location 'localhost:8081/globetrotter/city/random?userName=headout' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
3. Get Hint for the game: 
   curl --location 'localhost:8081/globetrotter/city/10/hints/1' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
4. Get Options for the game:
   curl --location 'localhost:8081/globetrotter/city/10/Options' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
2. Check for Answer: curl --location 'localhost:8081/globetrotter/city/10/check?userId=headout&answer=Berlin' \
   --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
